import json
import re
import shutil
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
MODULE = ROOT / "mobile-api-automation"
RESULTS = MODULE / "target" / "allure-results"
EVIDENCE = MODULE / "target" / "test-evidence"

SCREENSHOTS = EVIDENCE / "screenshots"
LOGS = EVIDENCE / "logs"
SUMMARIES = EVIDENCE / "summaries"

for folder in (SCREENSHOTS, LOGS, SUMMARIES):
    folder.mkdir(parents=True, exist_ok=True)


def safe_name(value):
    value = re.sub(r"[^A-Za-z0-9._-]+", "_", value)
    return value.strip("._") or "unnamed_test"


def read_result(path):
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except (OSError, json.JSONDecodeError) as exc:
        print(f"Skipping invalid result {path.name}: {exc}")
        return None


if not RESULTS.exists():
    raise SystemExit(f"Allure results folder not found: {RESULTS}")

result_files = list(RESULTS.glob("*-result.json"))
if not result_files:
    raise SystemExit("No Allure *-result.json files found.")

counts = {"passed": 0, "failed": 0, "broken": 0, "skipped": 0, "other": 0}
processed = 0

for result_file in result_files:
    result = read_result(result_file)
    if not result:
        continue

    test_name = result.get("name") or result.get("fullName") or result_file.stem
    full_name = result.get("fullName", test_name)
    status = result.get("status", "unknown").lower()
    attachments = result.get("attachments", [])
    prefix = safe_name(test_name)

    processed += 1
    counts[status if status in counts else "other"] += 1

    summary_lines = [
        f"Test: {test_name}",
        f"Full name: {full_name}",
        f"Status: {status.upper()}",
        f"Duration: {result.get('start', 'N/A')} - {result.get('stop', 'N/A')}",
    ]

    details = result.get("statusDetails", {})
    if details.get("message"):
        summary_lines += ["", "Failure message:", details["message"]]

    summary_lines += ["", "Attachments:"]
    for attachment in attachments:
        attachment_name = attachment.get("name", "attachment")
        source = attachment.get("source")
        media_type = attachment.get("type", "")
        summary_lines.append(f"- {attachment_name} ({media_type})")

        if not source:
            continue

        source_path = RESULTS / Path(source).name
        if not source_path.is_file():
            print(f"Missing attachment for {test_name}: {source}")
            continue

        name_lower = attachment_name.lower()
        if "screenshot" in name_lower or media_type == "image/png":
            destination_folder = SCREENSHOTS
            suffix = ".png"
            label = "screenshot"
        elif "stack trace" in name_lower:
            destination_folder = LOGS
            suffix = ".txt"
            label = "stack_trace"
        else:
            # Preserve other attachments, such as execution summaries.
            suffix = source_path.suffix or ".txt"
            destination_folder = LOGS
            label = safe_name(attachment_name)

        destination = destination_folder / f"{status.upper()}_{prefix}_{label}{suffix}"
        shutil.copy2(source_path, destination)

    summary_path = SUMMARIES / f"{status.upper()}_{prefix}.txt"
    summary_path.write_text(
        "\n".join(summary_lines) + "\n",
        encoding="utf-8"
    )

# Copy the shared run log once. It is not a per-test log.
shared_log = MODULE / "target" / "logs" / "automation.log"
if shared_log.is_file():
    shutil.copy2(shared_log, LOGS / "automation_run.log")

print(f"\nProcessed {processed} Allure test results.")
print("Statuses:", counts)
print(f"Evidence folder: {EVIDENCE}")
print(f"Screenshots: {SCREENSHOTS}")
print(f"Logs and attachments: {LOGS}")
print(f"Test summaries: {SUMMARIES}")
