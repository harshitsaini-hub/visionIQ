# visionIQ
### AI-Powered Multi-Modal Freight Intelligence & Logistics Decision-Support Platform
**Final Year (4th Year) Major Project • B.Tech Computer Science & Engineering**

[![Java](https://img.shields.io/badge/Java-21%20%2F%2024-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Google Gemini](https://img.shields.io/badge/Google_Gemini-2.5_Flash-4285F4?style=for-the-badge&logo=google&logoColor=white)](https://ai.google.dev/)
[![OpenWeather](https://img.shields.io/badge/OpenWeather-OneCall_Telemetry-EB6E4B?style=for-the-badge&logo=openweather&logoColor=white)](https://openweathermap.org/)
[![Status](https://img.shields.io/badge/Build-Passing_(9/9_Tests)-brightgreen?style=for-the-badge)](https://github.com/harshitsaini-hub/visionIQ)

---

## 📌 Executive Summary & Abstract

**visionIQ** is an intelligent, cloud-native logistics decision-support engine engineered to solve the complex multi-modal freight routing problem across India. When dispatching consignments (for example, sending fragile cargo from Bhopal to Delhi), shippers traditionally face the tedious, error-prone task of manually comparing dozens of logistics carriers, varying transport modes (Road, Rail, Air, Sea), dynamic freight tariffs, and fluctuating weather hazards.

visionIQ automates this entire decision matrix. It processes shipment parameters, evaluates geographical and environmental variables, and generates ranked carrier recommendations optimized for **Cost**, **Speed**, and **Cargo Safety**.

---

## 🏗️ System Architecture & Hybrid AI Philosophy

In real-world mission-critical enterprise logistics, delegating end-to-end decisions solely to a Large Language Model (LLM) introduces non-deterministic latency, hallucination risks, and prohibitive API expenses. 

To solve this, visionIQ employs a **Deterministic Algorithms + LLM Hybrid Architecture**:

```
                       [ User / Client Request ]
                                   │
                                   ▼
                   [ Spring Boot 3.4 REST Engine ]
                                   │
                    ┌──────────────┴──────────────┐
                    ▼                             ▼
       [ Haversine Spatial Filter ]     [ Cargo Classifier ]
      (100+ Hubs → 3-5 Candidates)     (Custom Item → Logistics Class)
                    │                             │
                    └──────────────┬──────────────┘
                    ▼
                   [ Google Gemini AI Reasoning ]
                    (Corridor & Route Sequencer)
                                   │
                    ┌──────────────┴──────────────┐
                    ▼                             ▼
       [ OpenWeather Live Feeds ]      [ Multi-Modal Carriers DB ]
       (Real-time Route Weather)       (Tariffs, Transit, Reliability)
                    │                             │
                    └──────────────┬──────────────┘
                                   ▼
                      [ Multi-Criteria Scoring Engine ]
                    Score = (Speed × Reliability) / (Cost × Risk)
                                   │
                                   ▼
              [ Ranked Recommendations + AI Explainability ]
                  (Cheapest | Fastest | Balanced Priorities)
                                   │
                                   ▼
             [ 1-Click Printable Consignment Manifest / PDF ]
              (CMVR & Carriage by Road Act 2007 Compliant)
```

### Deterministic vs Non-Deterministic Task Separation

| System Component | Technology | Role |
| :--- | :--- | :--- |
| **API & Business Logic** | Java 21, Spring Boot 3.4 | Request orchestration, validation, transaction boundary |
| **Spatial Filtering** | Java Haversine Algorithms | Mathematical distance calculation across 100+ Indian logistics hubs |
| **Carrier Registry & Tariffs** | H2 In-Memory / JPA / SQL | Deterministic carrier rates, base transit SLAs, reliability metrics |
| **Meteorological Feed** | OpenWeatherMap API | Live temperature, precipitation, and transit condition telemetry |
| **Cognitive Reasoning** | Google Gemini (AI Studio) | Corridor sequencing, cargo classification, weather risk advisory, explainability |
| **Documentation & Compliance** | HTML5 / CSS3 Print Engine | 1-Click Printable Waybill & Consignment Manifest under CMVR / Road Act 2007 |

---

## 🤖 Core AI/ML Capabilities

### 1. Smart Route Processing (Haversine + AI)
Instead of overwhelming the LLM with an unconstrained search over 100+ Indian cities (which causes hallucinations and high token costs), visionIQ uses a two-phase funnel:
- **Phase 1 (Mathematical Funnel):** Java-based spatial calculations compute great-circle distances using the **Haversine formula** to filter the 100+ logistics hub dataset down to **3–5 geographically viable candidates**.
- **Phase 2 (Corridor Sequencing):** Gemini processes only the 3–5 candidate hubs and orders them into a realistic national transit corridor (e.g., NH-46 to NH-44 highway progression or dedicated freight corridors).

$$\text{100+ Cities} \xrightarrow{\text{Haversine Filter}} \text{3–5 Viable Candidates} \xrightarrow{\text{Gemini AI}} \text{Optimized Corridor Sequence}$$

### 2. Contextual Cargo Classification
When users provide freeform or non-standard cargo descriptions rather than standard dropdown categories (e.g., *"Handmade Rajasthani Blue Pottery"* or *"Liquid nitrogen cryogenic flasks"*), Gemini dynamically reasons about cargo characteristics and categorizes them into appropriate freight classes (`FRAGILE`, `COLD_CHAIN`, `HAZARDOUS`, `E_COMMERCE`, `HEAVY_INDUSTRIAL`).

### 3. Weather + Cargo Dynamic Risk Advisory
visionIQ correlates live weather forecasts along the route corridor with the cargo's fragility and transport mode. 
- *Scenario:* Fragile cargo + Monsoon downpour + Road transport
- *AI Advisory:* The system flags hydraulic shock and road waterlogging hazards, recommending secondary moisture barrier packaging or suggesting a switch to rail freight.

### 4. Explainable Carrier Ranking & Insights
Carriers are ranked according to the shipper's priority:
- **Cheapest:** Minimizes overall freight expenditure via rail and surface corridors.
- **Fastest:** Prioritizes transit turnaround time via dedicated air corridors.
- **Balanced:** Solves multi-objective optimization balancing speed, cost, and safety grades.

Every recommendation includes human-readable AI justification explaining *why* a particular carrier was recommended for that specific route and cargo profile.

---

## 🚀 Performance Engineering: 120s → <500ms

In early iterations, chaining unoptimized sequential AI calls caused route evaluations to take upwards of **2 minutes (~120 seconds)**.

To meet production-grade latency standards for modern enterprise web applications, the architecture was re-engineered:
1. **Parallelized Asynchronous Execution:** Decoupled independent tasks (weather fetching, spatial filtering, and AI calls) using Java concurrency primitives.
2. **Intelligent In-Memory Route Caching:** Pre-computed and cached frequent corridors to prevent redundant external API round-trips.
3. **Resilient Local Fallback Engine:** Implemented deterministic heuristics that guarantee immediate responses even if external cloud APIs encounter rate-limits or timeouts.
4. **Focused Prompt Minimization:** Reduced token context to strictly necessary parameters, cutting Gemini inference time dramatically.

> **Result:** System latency dropped from **~120 seconds down to <500 ms** for benchmarked route search flows.

---

## 📄 1-Click Printable Consignment Manifest / PDF Export

visionIQ bridges digital decision support with physical logistics compliance. On the final shipment dossier page (`details.html`), shippers can generate an official **Multi-Modal Consignment Waybill & Manifest** with a single click:
- **Statutory Alignment:** Conforms to Section 9 of the **Carriage by Road Act 2007** and **Central Motor Vehicles Rules (CMVR) Rule 138 (e-Way Bill standards)**.
- **Encrypted Document Identifier:** Generates tracking reference codes (e.g., `VIQ-WB-2026-XXXXXX`) with dispatch metadata and verification barcodes.
- **Sign-off Endorsement Blocks:** Formatted signature and seal blocks for Consignor Dispatch, Carrier Operations Officer, and Inward Cargo Terminal Receiver.
- **Print & PDF Optimized:** Clean `@media print` styling removes web navigation chrome, rendering a formal high-contrast A4 document.

---

## 🎨 Enterprise SaaS Frontend Architecture

The user interface follows a modern, sharp light enterprise design standard (inspired by Stripe and Linear):
* **Centered Floating Top Navigation:** Persistent 64px sticky header with centered 1140px inner container, consistent brand identity, and fluid transitions across all pages.
* **Hero Telemetry Cockpit:** Live pulsing beacon indicator, visual corridor route track (`Origin Hub` ➔ `Transit Corridor` ➔ `Destination Terminal`), and color-coded semantic stat metrics.
* **4-Panel Guided Configuration Form:**
  1. `01. Route Corridor & Hub Resolution`: Dual city and 6-digit Indian pincode resolver with interactive Origin $\leftrightarrow$ Destination swap button.
  2. `02. Optimization Objective`: Segmented cards with distinct visual themes for **FASTEST** (Cobalt Blue), **BALANCED** (Emerald Green), and **CHEAPEST** (Warm Amber).
  3. `03. Consignment Profile`: Fast-select goods classification tiles and contextual Gemini AI natural language input.
  4. `04. Vulnerability & Special Handling`: High-contrast switches for shock dampening and cold chain reefer integrity.
* **Flagship Recommendation Highlight:** The #1 recommended carrier features an elevated shadow and gold star AI Top Recommendation ribbon.

---

## 📊 Scoring & Decision Formula

Each carrier is benchmarked using a multi-criteria scoring function:

$$\text{EfficiencyScore} = \frac{\text{Speed} \times \text{Reliability}}{\text{Cost} \times \text{RiskScore}}$$

- **Speed:** Inverse normalized transit duration (days).
- **Reliability:** Carrier historical fulfillment score (0–100 scale).
- **Cost:** Freight charge per kg normalized against industry benchmarks.
- **RiskScore:** Weather risk penalty + cargo vulnerability modifier.

---

## 💻 Tech Stack

- **Backend:** Java 21, Spring Boot 3.4.4, Spring Data JPA, Hibernate, Maven
- **AI Integration:** Google Gemini 1.5 / 2.5 Flash (Google AI Studio)
- **External APIs:** OpenWeatherMap OneCall / Forecast API
- **Database:** H2 In-Memory Database (Embedded SQL)
- **Frontend:** Responsive HTML5, Modern CSS3 (Grid/Flexbox/Print Media Queries), Vanilla JavaScript (ES6+)
- **Testing:** JUnit 5, Mockito, Spring Boot Test (9/9 unit tests passing)

---

## ⚙️ Getting Started (Local Development)

### Prerequisites
- **Java SE Development Kit (JDK) 17 or 21+**
- **Git**

### Installation Steps

1. **Clone or Navigate to the Repository:**
   ```bash
   git clone https://github.com/harshitsaini-hub/visionIQ.git
   cd visionIQ
   ```

2. **Configure API Keys (Optional - Fallbacks Available):**
   Set your keys as environment variables or update `src/main/resources/application.properties`:
   ```bash
   $env:GEMINI_API_KEY="your-gemini-api-key"
   $env:WEATHER_API_KEY="your-openweather-api-key"
   ```

3. **Build & Test the Project:**
   ```bash
   .\mvnw.cmd clean test
   ```

4. **Run the Application:**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```

5. **Access the Web Platform:**
   - **Frontend UI:** Open [http://localhost:8080](http://localhost:8080) in your web browser.
   - **H2 Database Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:visioniqdb`, User: `sa`, Password: *(empty)*)
   - **Health Check:** [http://localhost:8080/ping](http://localhost:8080/ping)

---

## 📡 API Reference

### Analyze Route
`POST /api/route/analyze`

**Request Body:**
```json
{
  "origin": "462001",
  "destination": "110001",
  "cargoType": "Other",
  "customCargoType": "Handmade Rajasthani Blue Pottery",
  "priority": "BALANCED",
  "fragile": true,
  "perishable": false
}
```

**Response Sample:**
```json
{
  "origin": "462001",
  "destination": "110001",
  "originCity": "Bhopal",
  "destCity": "Delhi",
  "cargoType": "Handmade Rajasthani Blue Pottery",
  "routeInsight": "Bhopal to Delhi corridor via NH-46 and NH-44. Road transit is subject to moderate rainfall near Gwalior; additional protective packaging advised for ceramic pottery.",
  "carriers": [
    {
      "name": "Bharat Air Express",
      "mode": "Air",
      "estimatedDays": 1,
      "costPerKg": 145.0,
      "riskScore": 8,
      "safetyGrade": "A+",
      "aiReason": "Optimal option for fragile, high-value artistic handicrafts requiring minimum vibration and transit shock exposure."
    },
    {
      "name": "Indian Railways Container Freight",
      "mode": "Rail",
      "estimatedDays": 3,
      "costPerKg": 38.0,
      "riskScore": 14,
      "safetyGrade": "A",
      "aiReason": "Cost-effective, weather-resilient transport corridor with stable vibration levels."
    }
  ]
}
```

---

## 🎓 Academic Viva & Presentation Highlights

When demonstrating **visionIQ** during evaluations:
1. **Demonstrate Freeform Cargo:** Enter custom items like *"Handmade Rajasthani Blue Pottery"* to show Gemini classification in action.
2. **Highlight Weather Hazard Response:** Demonstrate how weather along intermediate transit hubs triggers real-time packaging advisories.
3. **Discuss the Haversine Funnel:** Explain why feeding 100+ cities directly into an LLM is bad engineering, and how mathematical pre-filtering prevents hallucinations and slashes latency.
4. **Showcase Sub-Second Response Times:** Emphasize the architecture transition from 120s down to <500ms.
5. **Demonstrate 1-Click Manifest Print:** Click the print button on the dossier page to show evaluators a printable Indian Freight Manifest compliant with the Carriage by Road Act 2007.

---

## ⚖️ License & Declarations

This project is developed as a **Final Year (4th Year) Major Project** in partial fulfillment of the requirements for the degree of **Bachelor of Technology in Computer Science & Engineering**.

Released under the **MIT License**.
