
# Architectural Style Recommendation
## Test Genie – AI‑Driven Test Case Generator

---

## 1. Overview

This document describes the recommended **architectural style** for the *Test Genie* application based on comprehensive analysis including functional and non‑functional requirements, C4 models, Kubernetes deployment design, STRIDE threat modeling, architectural guardrails, and risk assessment.

The chosen style ensures scalability, security, governance, and safe adoption of GenAI within BCCH enterprise constraints.

---

## 2. Recommended Architectural Style

### **Policy‑Governed, Event‑Driven Microservices Architecture**

> A composite architecture that combines:
> - Microservices
> - Event‑Driven Architecture (EDA)
> - Mandatory Human‑in‑the‑Loop (HITL)
> - Strong policy enforcement (security, quality, compliance)

This style is purpose‑built for AI‑assisted enterprise systems where automation must be balanced with governance and accountability.

---

## 3. Key Architectural Principles

### 3.1 Microservices (Structural Style)

Each major business capability is implemented as an independently deployable service:

- Requirement Ingestion Service
- Quality Validation Service
- Context Enrichment Service
- GenAI Test Case Generation Service
- HITL Review & Approval Service
- Regression Impact Analysis Service
- Publishing & Integration Service
- Audit & Logging Service

**Benefits:**
- Independent scaling and deployment
- Clear bounded contexts
- Reduced blast radius
- Natural alignment with Kubernetes

---

### 3.2 Event‑Driven Architecture (Interaction Style)

Long‑running and asynchronous workflows are orchestrated using events and queues.

Key domain events include:
- `requirement.ingested`
- `requirement.validated`
- `testcase.generation.requested`
- `testcase.generated`
- `testcase.approved`
- `regression.impact.detected`
- `release.approved`

**Benefits:**
- Non‑blocking AI execution
- Decoupled services
- High scalability for AI workloads
- Event replay and auditability

---

### 3.3 Human‑in‑the‑Loop (HITL) as a Core Constraint

Human approval is a **first‑class architectural rule**, not a workflow afterthought.

Rules enforced architecturally:
- AI can never publish autonomously
- QA Lead approval required for release‑critical tests
- Overrides require justification and audit logging

**Benefits:**
- AI hallucination risk mitigation
- Accountability and compliance
- Increased trust in AI outputs

---

### 3.4 Policy‑Governed Architecture

Cross‑cutting policies are enforced through architecture rather than conventions:

| Policy Type | Enforced Via |
|------------|-------------|
| Security | RBAC, NetworkPolicy, Trust Boundaries |
| Quality | Validation Gate + Scoring Engine |
| AI Usage | Prompt & Output Guardrails |
| Traceability | Central Metadata Repository |
| Release Control | HITL + Release Approval |

---

## 4. Supporting Architectural Styles

### 4.1 Hexagonal (Ports & Adapters)

Used within microservices to isolate core logic from:
- External systems (GitLab, Squash)
- AI engines
- Persistence layers

### 4.2 Layered Architecture (Within Services Only)

Each service follows a clean internal layering:
- API / Controller Layer
- Domain Logic Layer
- Infrastructure Layer

---

## 5. Styles Explicitly Not Chosen

### ❌ Monolithic Architecture
- Poor AI scalability
- High blast radius
- Weak governance separation

### ❌ Purely Synchronous Microservices
- Blocking AI calls
- Cascading failures
- Poor user experience

### ❌ Pure Serverless Architecture
- Complex HITL orchestration
- Cost unpredictability for AI jobs
- Weak state management for approvals

---

## 6. Alignment with Existing Artefacts

The recommended style aligns with all previously produced artefacts:

- C4 Context / Container / Component / Deployment diagrams
- Kubernetes namespaces, RBAC, NetworkPolicy
- STRIDE threat modeling
- Event taxonomy & async sequences
- ADR‑001 (HITL), ADR‑004 (Event‑Driven AI)
- Risk R‑13 mitigation (regression gaps)

This confirms architectural consistency and coherence.

---

## 7. Executive Summary

**Recommendation:**

> Adopt a *Policy‑Governed, Event‑Driven Microservices Architecture* with mandatory *Human‑in‑the‑Loop controls*, deployed on Kubernetes with strong isolation, asynchronous AI processing, and full auditability.

This architectural style:
- Enables safe, scalable AI adoption
- Meets BCCH security and governance requirements
- Supports future extensibility and deployment variants
- Minimizes operational and quality risk

---

## 8. Status

- **Architecture Style:** Approved
- **Applicable To:** MVP and future phases
- **Governance:** Non‑negotiable guardrails apply across all deployments
