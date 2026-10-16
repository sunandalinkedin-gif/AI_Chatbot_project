# 🤖 AI Chatbot Android Application

[![Android](https://img.shields.io/badge/Platform-Android-3D5E82?style=flat-square&logo=android&logoColor=white)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Groq API](https://img.shields.io/badge/AI-Groq%20API-F55036?style=flat-square)](https://groq.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](LICENSE)

A clean, modern, and lightweight Android chat application built with Kotlin. It connects directly to the **Groq API** to deliver lightning-fast AI conversational responses, featuring rich Markdown formatting, edge-to-edge UI layout with soft keyboard (IME) awareness, and customized chat bubbles.

---

## ✨ Key Features

- **⚡ High-Speed AI Responses:** Integrates with the Groq API (`qwen/qwen3.8-27b` model by default) using Volley for fast networking.
- **📝 Rich Markdown Formatting:** AI responses are dynamically rendered with Markdown support via **Markwon**.
- **📱 Edge-to-Edge & IME Inset Handling:** Built with modern Android edge-to-edge window insets listener that dynamically adjusts bottom padding when the software keyboard opens, keeping the input box pinned right above the keyboard.
- **🎨 Custom Styled Interface:**
  - **Background:** Steel Gray (`#607D8B`)
  - **User Input Box:** Light Blue (`#E3F2FD`) with high-contrast black text.
  - **User Chat Bubble:** Soft Green (`#C8E6C9`)
  - **AI Response Bubble:** Soft Purple (`#E1BEE7`)
- **♻️ Efficient RecyclerView:** Smooth scrolling chat log handling thousands of messages.

---

## 🛠️ Tech Stack & Dependencies

- **Language:** [Kotlin](https://kotlinlang.org)
- **UI Framework:** Android Views (`ConstraintLayout`, `RecyclerView`, `CardView`)
- **Networking:** [Android Volley](https://developer.android.com/training/volley)
- **Markdown Rendering:** [Markwon Core](https://noties.io/Markwon/)
- **Build System:** Gradle with Version Catalogs (`libs.versions.toml`) & AGP 9.3.0

---

## 📂 Project Architecture & Structure

```text
app/
├── src/main/
│   ├── java/com/example/testapplication/
│   │   ├── ChatActivity.kt      # Main Activity handling chat UI, input events, & Groq API calls
│   │   ├── ChatAdapter.kt       # RecyclerView adapter handling User & AI message view types
│   │   └── ChatModel.kt         # Data model representing individual chat messages
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_chat.xml # Main chat screen layout (Steel Gray background)
│   │   │   ├── user_chat.xml     # User chat bubble layout (Green)
│   │   │   └── ai_bot.xml        # AI response chat bubble layout (Purple)
│   │   └── values/               # Colors, themes, and string resources
│   └── AndroidManifest.xml
```

---

## 🚀 Getting Started & Setup

### Prerequisites
- Android Studio (Arctic Fox or newer recommended)
- Android SDK (Compile SDK 37 / Min SDK 24)

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/ai-chatbot-android-app.git
```

### 2. Configure Your Groq API Key
1. Open the project in Android Studio.
2. Navigate to:
   `app/src/main/java/com/example/testapplication/ChatActivity.kt`
3. Locate the `GROQ_API_KEY` variable (around line 81):
   ```kotlin
   val GROQ_API_KEY = "YOUR_API_KEY_HERE"
   ```
4. Replace `"YOUR_API_KEY_HERE"` with your actual Groq API key:
   ```kotlin
   val GROQ_API_KEY = "gsk_YourActualGroqApiKeyHere"
   ```

### 3. Build & Run
- Sync your project with Gradle files.
- Select an emulator or connected device and click **Run**.

---

## 📱 Screenshots & Demo

> [!NOTE]
> Replace the placeholder paths below with your actual screenshot and video files.

### 🖼️ UI Preview
| Initial Chat Screen | Active Conversation |
| :---: | :---: |
| ![Initial Screen](./screenshots/chat_screen_initial.png) | ![Active Chat](./screenshots/chat_screen_active.png) |

### 🎬 Video Walkthrough
[![Watch the video demo](./screenshots/video_thumbnail.png)](./videos/app_walkthrough.mp4)

---

## 📄 License

Distributed under the MIT License. See [LICENSE](LICENSE) for more information.
