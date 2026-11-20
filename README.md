# visionIQ
### AI-Powered Multi-Modal Logistics Decision-Support Platform
**Final Year (4th Year) Major Project**

---

## 📌 Executive Summary & Abstract

**visionIQ** is an intelligent, cloud-native logistics decision-support engine engineered to solve the complex multi-modal freight routing problem across India. When shipping consignments (for example, sending fragile cargo from Bhopal to Delhi), shippers traditionally face the tedious, error-prone task of manually comparing dozens of logistics carriers, varying transport modes (Road, Rail, Air, Sea), dynamic freight tariffs, and fluctuating weather hazards.

visionIQ automates this entire decision matrix. It processes shipment parameters, evaluates geographical and environmental variables, and generates ranked carrier recommendations optimized for **Cost**, **Speed**, and **Cargo Safety**.

---

## 🏗️ System Architecture & Hybrid AI Philosophy

In real-world mission-critical enterprise logistics, delegating end-to-end decisions solely to a Large Language Model (LLM) introduces non-deterministic latency, hallucination risks, and high API expenses. 

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
```

### Deterministic vs Non-Deterministic Task Separation

| System Component | Technology | Role |
| :--- | :--- | :--- |
| **API & Business Logic** | Java 21, Spring Boot 3.4 | Request orchestration, validation, transaction boundary |
| **Spatial Filtering** | Java Haversine Algorithms | Mathematical distance calculation across 100+ Indian logistics hubs |
| **Carrier Registry & Tariffs** | H2 In-Memory / JPA / SQL | Deterministic carrier rates, base transit SLAs, reliability metrics |
| **Meteorological Feed** | OpenWeatherMap API | Live temperature, precipitation, and transit condition telemetry |
| **Cognitive Reasoning** | Google Gemini (AI Studio) | Corridor sequencing, cargo classification, weather risk advisory, explainability |

---

## 🤖 Core AI/ML Capabilities

### 1. Smart Route Processing (Haversine + AI)
Instead of overwhelming the LLM with an unconstrained search over 100+ Indian cities (which causes hallucinations and high token costs), visionIQ uses a two-phase funnel:
- **Phase 1 (Mathematical Funnel):** Java-based spatial calculations compute great-circle distances using the **Haversine formula** to filter the 100+ logistics hub dataset down to **3–5 geographically viable candidates**.
- **Phase 2 (Corridor Sequencing):** Gemini processes only the 3–5 candidate hubs and orders them into a realistic national transit corridor (e.g., NH-44 highway progression or dedicated rail corridors).

$$\text{100+ Cities} \xrightarrow{\text{Haversine Filter}} \text{3–5 Viable Candidates} \xrightarrow{\text{Gemini AI}} \text{Optimized Corridor Sequence}$$

### 2. Contextual Cargo Classification
When users provide freeform or non-standard cargo descriptions rather than standard dropdown categories (e.g., *"Handmade Rajasthani Blue Pottery"* or *"Liquid nitrogen cryo-containers"*), Gemini dynamically reasons about cargo characteristics and categorizes them into appropriate freight classes (`FRAGILE`, `COLD_CHAIN`, `HAZARDOUS`, `E_COMMERCE`, `HEAVY_INDUSTRIAL`).

### 3. Weather + Cargo Dynamic Risk Advisory
visionIQ correlates live weather forecasts along the route corridor with the cargo's fragility and transport mode. 
- *Scenario:* Fragile cargo + Monsoon downpour + Road transport
- *AI Advisory:* The system flags hydraulic shock and road waterlogging hazards, recommending secondary moisture barrier packaging or suggesting a switch to rail freight.

### 4. Explainable Carrier Ranking & Insights
Carriers are ranked according to the shipper's priority:
- **Cheapest:** Minimizes overall freight expenditure.
- **Fastest:** Prioritizes transit turnaround time.
- **Balanced:** Solves multi-objective optimization balancing speed, cost, and safety grades.

Every recommendation includes human-readable AI justification explaining *why* a particular carrier was recommended for that specific route and cargo profile.

---

## 🚀 Performance Engineering: 120s → <500ms

In early iterations, chaining unoptimized sequential AI calls caused route evaluations to take upwards of **2 minutes (~120 seconds)**.

To meet production-grade latency standards for modern web applications, the architecture was re-engineered:
1. **Parallelized Asynchronous Execution:** Decoupled independent tasks (weather fetching, spatial filtering, and AI calls) using Java concurrency primitives.
2. **Intelligent In-Memory Route Caching:** Pre-computed and cached frequent corridors to prevent redundant external API round-trips.
3. **Resilient Local Fallback Engine:** Implemented deterministic heuristics that guarantee immediate responses even if external cloud APIs encounter rate-limits or timeouts.
4. **Focused Prompt Minimization:** Reduced token context to strictly necessary parameters, cutting Gemini inference time dramatically.

> **Result:** System latency dropped from **~120 seconds down to <500 ms** for benchmarked route search flows.

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
- **AI Integration:** Google Gemini 1.5 / 3.1 Flash (Google AI Studio)
- **External APIs:** OpenWeatherMap OneCall / Forecast API
- **Database:** H2 In-Memory Database (Embedded SQL)
- **Frontend:** Responsive HTML5, Modern CSS3 (Grid/Flexbox/Transitions), Vanilla JavaScript (ES6+)
- **Testing:** JUnit 5, Mockito, Spring Boot Test

---

## ⚙️ Getting Started (Local Development)

### Prerequisites
- **Java SE Development Kit (JDK) 17 or 21+**
- **Git**

### Installation Steps

1. **Clone or Navigate to the Repository:**
   ```bash
   cd D:\JavaProjects\visionIQ
   ```

2. **Configure API Keys (Optional - Fallbacks Available):**
   Set your keys as environment variables or update `src/main/resources/application.properties`:
   ```bash
   $env:GEMINI_API_KEY="your-gemini-api-key"
   $env:WEATHER_API_KEY="your-openweather-api-key"
   ```

3. **Build the Project:**
   ```bash
   .\mvnw.cmd clean package
   ```

4. **Run the Application:**
   ```bash
   .\mvnw.cmd spring-boot:run
   ```
   *(Or execute `.\start.ps1`)*

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
  "origin": "Bhopal",
  "destination": "Delhi",
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
  "origin": "Bhopal",
  "destination": "Delhi",
  "cargoType": "Handmade Rajasthani Blue Pottery",
  "routeInsight": "Bhopal to Delhi route via NH-46 and NH-44. Road transit is subject to moderate rainfall near Gwalior; additional protective packaging advised.",
  "carriers": [
    {
      "name": "Bharat Air Express",
      "mode": "Air",
      "estimatedDays": 1,
      "costPerKg": 145.0,
      "riskScore": 8,
      "safetyGrade": "A+",
      "aiReason": "Optimal option for fragile, high-value artistic handicrafts requiring minimum shock exposure."
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

## 🎓 Academic Presentation Highlights

When demonstrating **visionIQ** during evaluations:
1. **Demonstrate Freeform Cargo:** Enter custom items like *"Handmade Rajasthani Blue Pottery"* to show Gemini classification in action.
2. **Highlight Weather Hazard Response:** Demonstrate how weather along intermediate transit hubs triggers real-time packaging advisories.
3. **Discuss the Haversine Funnel:** Explain why feeding 100+ cities directly into an LLM is bad engineering, and how mathematical pre-filtering prevents hallucinations and slashes latency.
4. **Showcase Sub-Second Response Times:** Emphasize the architecture transition from 120s down to <500ms.
