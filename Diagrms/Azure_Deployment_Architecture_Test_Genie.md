
# Azure Deployment Architecture
## Test Genie – AI‑Driven Test Case Generator

---

## 1. Purpose

This document defines the **Azure deployment architecture** for *Test Genie*, derived from the approved **Policy‑Governed, Event‑Driven Microservices Architecture with mandatory Human‑in‑the‑Loop (HITL)**.

The goal is to provide a secure, scalable, compliant, and cloud‑native deployment aligned with BCCH and Cognizant enterprise standards.

---

## 2. Core Azure Architectural Principles

- **Zero Trust Security** (no implicit trust between components)
- **Internal‑only GenAI** (no external AI data exposure)
- **Event‑driven processing** for AI & regression workloads
- **Kubernetes‑first** microservices platform
- **Mandatory human approval** before publish / release
- **Audit‑ready by design**

---

## 3. Recommended Azure Services

### 3.1 Identity & Access Management

- **Azure Active Directory (Microsoft Entra ID)**
  - User authentication
  - Role‑based access control
  - MFA enforcement
- **Azure MyApps / Enterprise Applications**
  - Access request and approval workflow
- **Azure Key Vault**
  - Secrets, certificates, keys

---

### 3.2 Edge, Networking & Security

- **Azure Front Door**
  - Global entry point
  - SSL termination
- **Azure Web Application Firewall (WAF)**
  - OWASP Top‑10 protection
- **Azure Virtual Network (VNet)**
  - Network isolation
- **Private Endpoints**
  - No public data or AI exposure

---

### 3.3 Application Platform

- **Azure Kubernetes Service (AKS)**
  - Core runtime platform
  - Independent microservice scaling
  - Namespace‑based isolation

Namespaces:
- `testgenie-ui`
- `testgenie-core`
- `testgenie-ai`
- `platform-services`
- `data-layer`

---

### 3.4 Core Microservices (AKS)

- Requirement Ingestion Service
- Requirement Validation & Scoring Service
- Context Enrichment Service
- HITL Review & Approval Service
- Regression Impact Analyzer
- Test Case Publishing Service
- Audit & Logging Service

Each service is deployed as:
- Independent Kubernetes Deployment
- Dedicated ServiceAccount
- RBAC & NetworkPolicy enforced

---

### 3.5 GenAI Deployment

- **Preferred:** Self‑hosted GenAI inference in AKS
  - Dedicated node pool (CPU/GPU)
  - Private networking

- **Optional (Policy‑Approved):** Azure OpenAI
  - Private Endpoint only
  - No data retention
  - No model training on BCCH data

---

### 3.6 Event‑Driven Backbone

- **Azure Event Hubs (Kafka‑compatible)**
  - Async orchestration of AI workflows
  - Decouples UI from GenAI workloads

Key events:
- `requirement.ingested`
- `requirement.validated`
- `testcase.generated`
- `testcase.approved`
- `regression.impact.detected`
- `release.approved`

---

### 3.7 Data & Audit

- **Azure SQL Database / Azure PostgreSQL**
  - Requirement → Test traceability
- **Azure Blob Storage**
  - Requirement documents, artifacts
- **Azure Monitor & Log Analytics**
  - Centralized, immutable audit logs

All data stores use:
- Encryption at rest
- Private endpoints
- Controlled access via managed identities

---

### 3.8 Observability & Security Posture

- **Azure Application Insights**
  - Distributed tracing
- **Azure Monitor**
  - Metrics and alerting
- **Microsoft Defender for Cloud**
  - Threat detection
  - Posture management

---

## 4. Deployment View Summary

| Layer | Azure Services |
|-----|----------------|
| User Access | Browser, Azure Front Door |
| Identity | Azure AD, MyApps |
| Edge Security | WAF, Front Door |
| Platform | AKS |
| AI | AKS (GPU pool) / Azure OpenAI |
| Eventing | Azure Event Hubs |
| Data | Azure SQL, Blob Storage |
| Audit | Monitor, Log Analytics |

---

## 5. Mapping to Architecture Decisions

| ADR | Azure Alignment |
|----|----------------|
| ADR‑001 (HITL) | AKS service‑level approval gates |
| ADR‑002 (Internal AI) | Private GenAI in AKS |
| ADR‑003 (Regression Control) | HITL + Event Hubs |
| ADR‑004 (Event‑Driven AI) | Azure Event Hubs |
| ADR‑005 (Isolation) | AKS namespaces + RBAC |

---

## 6. Security & Compliance Assurance

This Azure deployment:
- Prevents data exfiltration
- Enforces least‑privilege access
- Mitigates STRIDE threats
- Supports audit & compliance reviews
- Enables deployment variants (DEV / PROD / HA / DR)

---

## 7. Status

- **Deployment Architecture:** Approved
- **Cloud Platform:** Microsoft Azure
- **Applies To:** MVP and all future phases
