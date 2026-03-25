BCCH F2B Testing – 1. Analytics Genie: AI Driven Test Case Generator (Draft - WIP)

Table of Contents

1 Product Overview ............................................................................................................ 3
2 Problem Statement ......................................................................................................... 4
3 MVP ( 13th March 2026 ) ............................................................................................... 5
3.1 Access right needed to access Test Genie (MVP) ....................................................... 5
4 Product Flow Chart ( WIP) .............................................................................................. 6
5 Solution Summary........................................................................................................... 7
6 Product Value Summary.................................................................................................. 8
7 Target Users ................................................................................................................... 9
8 Key Use Cases.............................................................................................................. 10
9 High-Level Product Features......................................................................................... 11
9.1 Orchestration Agent UI ............................................................................................... 11
9.2 Gaurdrails ................................................................................................................... 11
9.3 Requirement Ingestion Module.................................................................................... 11
9.4 Context Enrichment (Optional but Recommended)...................................................... 11
9.5 Requirement Validation & Quality Scoring .................................................................. 12
9.6 Test case Generation Agent........................................................................................ 12
9.7 Human-in-the-Loop (HITL) Review............................................................................. 13
9.8 Continuous Improvement Loop ................................................................................... 13
10 Non-Functional Requirements ..................................................................................... 14
11 Input Expectations / Constraints .................................................................................. 15
12 Workflow Summary ..................................................................................................... 16
13 Backlog Items / Future Enhancements ........................................................................ 17

1 Product Overview

The AI-Driven Test Case Generator Utility is an internal BCCH F2B Digital Testing solution that automates the end-to-end process of creating comprehensive test cases from requirements originating from multiple systems such as GitLab, Squash, Word, and PDF documents.
It supports structured, unstructured, multilingual (including Chinese), and visually rich requirement formats.

The product not only generates test cases but also evaluates requirement quality, recommends improvements, and ensures regression packs stay updated.

2 Problem Statement

Today, writing high-quality test cases is:

Time-consuming (1–3 days per requirement)

Manually intensive

Error-prone for complex or multilingual requirements

Difficult when dealing with tables, images, or poorly written requirements

Dependent on tester experience and interpretation

Difficult when requirements come in inconsistent formats, languages, or levels of detail

Regression packs become outdated quickly, requiring manual effort to identify impacted tests.
The lack of standardization and speed slows down F2B delivery and leads to test execution delays. Teams need an AI-powered solution to ensure faster, more accurate, consistent, and comprehensive testing.

3 MVP ( 13th March 2026 )

3.1 Access right needed to access Test Genie (MVP)

Code snippet
graph TD
    A[User requests access via MyApps] --> B{Is User in BCCH F2B AD Group?}
    B -- Yes --> C[Access Granted to Test Genie UI]
    B -- No --> D[Request routed to Line Manager for Approval]
    D -- Approved --> E[IT Security provisions access]
    E --> C
    D -- Rejected --> F[Access Denied]
4 Product Flow Chart (WIP)

Code snippet
graph TD
    Start([Start]) --> Ingest[Ingest Requirement: GitLab/Word/PDF]
    Ingest --> Validate{Validate Quality & Format}
    Validate -- Fails --> Warn[Display Warnings / Gaps]
    Warn --> UserFix[User Updates Requirement]
    UserFix --> Validate
    Validate -- Passes / User Overrides --> Context[Add Business/App Context]
    Context --> GenAI[AI Generates Test Cases]
    GenAI --> HITL[Human-in-the-Loop Review]
    HITL --> Approve{Approved?}
    Approve -- No --> Edit[Tester edits steps manually]
    Edit --> HITL
    Approve -- Yes --> Publish[Publish to Squash / Test Management]
    Publish --> End([End])
5 Solution Summary

The Test Case Generator Utility solves these challenges by:

Automatically reading requirements from multiple sources (GitLab, Squash, PDF, Word; future: Confluence)

Handling structured or unstructured content

Supporting multiple languages, including full Chinese documents

Understanding tables, images, and multi-page documents

Producing complete positive and negative test scenarios

Allowing testers to add application context + business context

Validates mandatory fields: clarity, testability, traceability, prerequisites, acceptance criteria.

Rates requirement quality per BCCH guidelines.

Provides warnings when requirements are unclear or incomplete.

Allows user to correct requirement or proceed with warnings.

Generating clear preconditions, test steps, expected results

This ensures high coverage, consistency, and drastic reduction in test creation time.

6 Product Value Summary

This utility will become a daily essential tool for BCCH F2B testers, improving:

Speed

Coverage

Consistency

Quality

Traceability

It transforms requirement → test case creation from a slow, manual task into an automated, high-quality AI workflow.

7 Target Users

Primary users:

BCCH Front-to-Back Testing Community

Testers across F2BD release cycles

QA Leads, Test Coordinators, Testing Pods

Frequency of use:

Daily, especially during new releases or requirement onboarding

Used whenever new requirements arrive and test cases must be built quickly

8 Key Use Cases

Generate test cases from a single requirement

Generate test cases from multiple related requirements

Generate test cases from Word/PDF uploads

Generate test cases from GitLab or Squash stories

Generate multilingual test cases (English, Chinese, mixed languages)

Handle Structured as well as unstructured requirement documents

Build test cases for complex requirements containing tables, images, or diagrams

9 High-Level Product Features

9.1 Orchestration Agent UI
Front-end UI (orchestration layer) includes:

User prompt input

Requirement ingestion area

Context fields (application + business context)

Real-time status updates - Requirement validator feedback on quality score/gap, Test case generation

Test case output preview

9.2 Gaurdrails
GenAI inputs and outputs can be negatively impacted by different factors. To manage these factors, guardrails must be deployed to ensure that inputs and outputs are validated.
Questions to consider:
→ Types of Gaurdrails needed?
→ Criteria of a successful guardrail implementation?

9.3 Requirement Ingestion Module
Supports ingestion from:

GitLab Requirements

Squash Test Management

Word documents (.docx)

PDFs

(Backlog: Confluence integration)

Handles:

Structured & unstructured text

Multi-page documents

Tables, images, embedded content

(Backlog: Multilingual documents (including fully Chinese inputs))

9.4 Context Enrichment (Optional but Recommended)
Users can optionally provide:

Application context (system/module behavior, data flows)

Business context (client types, workflow steps, dependencies)

Additional user prompt - may be persona, condition to apply to all generated TCs, etc.

Known constraints

Environment nuances

This enriches the generative agent's understanding for more accurate test cases.

9.5 Requirement Validation & Quality Scoring
Before generating test cases, the system performs a mandatory quality assessment.
It checks whether the requirement includes:

Clear requirement statement

Acceptance criteria

Preconditions / prerequisites

Traceability

Testability indicators

Unambiguous expected outcomes

The system:

Rates the requirement quality using BCCH guidelines

Flags missing/ambiguous elements

Highlights exact areas needing improvement

User can:

Fix and re-upload updated requirement

Ignore warnings and proceed (at their own risk)

9.6 Test case Generation Agent
Once validation passes (or warnings overridden):
The AI Generator produces:

Positive test cases

Negative test cases

Boundary/edge cases

Preconditions

Step-by-step actions

Expected results

Requirement → Test case traceability links

Test cases reflect:

System behavior

Business workflow expectations

Application logic and variations

9.7 Human-in-the-Loop (HITL) Review
The user reviews the generated test cases:

Edits or refines steps

Adds or removes scenarios

Validates accuracy with Product Owner if needed

Marks test cases as "approved"

This ensures expert oversight and accountability.

9.8 Continuous Improvement Loop
Whenever updated requirements are provided:

The system revalidates them

Regenerates test cases

Reassesses regression impacts

Ensures regression test case continuous alignment with new changes

10 Non-Functional Requirements

Category	Requirement
Performance	Generate test cases within 1–3 minutes
Scalability	Support multi-page PDFs, large Word files, multiple requirements
Language Support	English, Chinese (full document), mixed formats
Reliability	Handle unsupported formats gracefully
Security	Internal-only, [obscured]-compliant, no external data exposure
Extensibility	Future connectors: Confluence, other test tools
11 Input Expectations / Constraints

The system expects requirements to include (at minimum):

Clear requirement statement(s)

Pre-conditions (if provided)

Acceptance criteria

Expected system behavior

But even if missing, the AI utility attempts to infer and fill gaps intelligently.

12 Workflow Summary

Ingest requirement → GitLab / Squash / Word / PDF

Validate requirement quality → mandatory field checks

Warn / Correct → user fixes or overrides

Enrich → add business + application context

Generate → AI produces full test-case suite

Review → human validation loop

Publish → auto-upload to Squash

Analyze Regression → recommend updates

Maintain → user applies regression changes

13 Backlog Items / Future Enhancements

Confluence ingestion

Better table understanding

Improved OCR for image-heavy PDFs

Conversational refinement mode

Direct Squash upload via DevLens

Multi-language output (ENG + CN test cases)

Test data suggestion engine

Regression Test case ingestion and recomened impacted test cases
