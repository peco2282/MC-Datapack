# MC-Datapack Agent Guidelines

## Project Overview
This is an IntelliJ Platform Plugin that adds language support for Minecraft Function (.mcfunction) files, enabling syntax highlighting, code completion, and formatting within IntelliJ IDEA. The plugin is built using Kotlin and Gradle, leveraging Grammar-Kit for parser generation and JFlex for lexing.

## Architecture
- **Language Support**: Core functionality resides in `src/main/kotlin/com/github/peco2282/mcdatapack/language/`, with subdirectories for completion, formatting, highlighting, parser, psi, reference, and structure.
- **Grammar Definition**: `language/mcfunction.bnf` defines the grammar for Minecraft commands (e.g., `execute`, `give`, `function`), parsed via Grammar-Kit.
- **Lexer**: `language/mcfunction.flex` handles tokenization, integrated with JFlex.
- **Extensions**: All plugin extensions are declared in `src/main/resources/META-INF/plugin.xml`, registering file types, syntax highlighters, annotators, and tool windows.
- **Services & UI**: Project services in `services/`, tool window in `toolWindow/`, and startup activities in `startup/` (note: much of this is template sample code to be customized).

## Key Workflows
- **Build Plugin**: Run `./gradlew buildPlugin` to generate the plugin JAR in `build/libs/`.
- **Run in IDE**: Use `./gradlew runIde` to launch IntelliJ IDEA with the plugin loaded for testing.
- **Test Coverage**: Execute `./gradlew koverReport` to generate coverage reports; view results in `build/reports/kover/`.
- **Code Quality**: Run `./gradlew qodana` for static analysis; configure rules in `qodana.yml`.
- **Changelog**: Update `CHANGELOG.md` using the Gradle Changelog Plugin; changes are automatically extracted for plugin manifests.

## Conventions
- **Dependencies**: Managed via Gradle version catalog in `gradle/libs.versions.toml`; add new dependencies there and reference via `libs.<name>`.
- **Plugin Configuration**: Properties like version, group, and platform version are set in `gradle.properties`; update `pluginVersion` for releases.
- **Resource Bundles**: UI strings in `src/main/resources/messages/MyBundle.properties`; access via `MyBundle.message(key)`.
- **PSI Elements**: Generated from `mcfunction.bnf`; custom PSI classes extend generated ones in `language/psi/impl/`.
- **File Structure**: Follow IntelliJ Platform Plugin Template structure; place language-specific code under `language/`, UI components under `toolWindow/`.

## Integration Points
- **Minecraft Commands**: Parser recognizes commands like `execute run function my:namespace` with proper nesting and references.
- **References**: `McFunctionReferenceContributor` handles cross-file references (e.g., function calls).
- **Completion**: `McFunctionCompletionContributor` provides context-aware suggestions for commands and arguments.
- **Formatting**: `McFunctionFormattingModelBuilder` applies consistent indentation and spacing.

## Debugging Tips
- Enable PSI viewer in IntelliJ (Tools > View PSI Structure) to inspect parsed .mcfunction files.
- Use `./gradlew runIde --debug-jvm` for remote debugging the plugin.
- Check `build/reports/tests/` for test failures; run `./gradlew test` to execute unit tests.

Reference: `README.md` for setup; `build.gradle.kts` for build logic; `plugin.xml` for extension points.</content>
<parameter name="filePath">D:\Workspace\plugins\MC-Datapack\AGENTS.md
