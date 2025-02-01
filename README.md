# 🪶 Feather Showcase

**Feather Showcase** is an example plugin demonstrating the capabilities of [FeatherToolkit](https://github.com/TheAncientOwl/feather-toolkit). This project serves as a reference implementation for developers looking to build plugins using the toolkit.

## 📌 Features

- Modular architecture powered by FeatherToolkit
- Example teleportation module
- Example event-driven dummy module
- Uses [Quill](https://github.com/TheAncientOwl/quill) for project management

## ⚙️ Setup

### 1️⃣ Prerequisites

Ensure you have the following installed:

- Java 21+
- Maven 3+
- [Quill](https://github.com/TheAncientOwl/quill)

### 2️⃣ Installation

Clone the repository and build the plugin:

```bash
$ git clone https://github.com/TheAncientOwl/feather-showcase.git
$ cd feather-showcase
$ quill -i
```

### 3️⃣ Running the Plugin

```bash
$ quill_run_server.sh
```

## 📖 Modules

### ✨ Teleport Module

Handles delayed player teleportation with cancelation upon movement.

**Key Classes:**

- `ITeleport.java` (Interface)
- `TeleportModule.java` (Module implementation)
- `StartTeleportCommand.java` (Command handler)
- `PlayerCancelTpOnMoveListener.java` (Event listener)

### ✨ Dummy Module

Demonstrates basic event handling by responding to red wool clicks.

**Key Classes:**

- `IDummy.java` (Interface)
- `DummyModule.java` (Module implementation)
- `PlayerRightClickRedWoolListener.java` (Event listener)

## 🛠️ Development

### 🏗️ Building

Compile the project with:

```bash
$ quill --i
```

### ✅ Testing

Run unit tests with:

```bash
$ quill -t
```

## 📜 License

This project is licensed under the **FeatherCore License**. See [LICENSE](https://github.com/TheAncientOwl/feather-showcase/blob/main/LICENSE) for details.

## 🔗 Related Projects

- [🪶 FeatherToolkit](https://github.com/TheAncientOwl/feather-toolkit)
- [🧰 Quill](https://github.com/TheAncientOwl/quill)
