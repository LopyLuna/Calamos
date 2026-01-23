plugins {
    id("java-library")
    id("maven-publish")
    id("net.neoforged.moddev") version "2.0.30-beta"
}

val build = System.getenv("GITHUB_RUN_NUMBER")?.let { "-build.$it" } ?: ""

version = "${property("mod_version")}+mc${property("minecraft_version")}$build"
if (System.getenv("BUILD_NUMBER") != null) {
    version = "$version.${System.getenv("BUILD_NUMBER")}"
}
base {
    archivesName.set(project.property("archives_base_name").toString())
}
group = "${property("mod_group_id")}"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

val localRuntime: Configuration by configurations.creating
configurations.runtimeClasspath {
    extendsFrom(localRuntime)
}

neoForge {
    version.set(project.property("neo_version").toString())

    parchment {
        mappingsVersion.set(project.property("parchment_mappings_version").toString())
        minecraftVersion.set(project.property("parchment_minecraft_version").toString())
    }

    setAccessTransformers(
        "src/main/resources/META-INF/accesstransformer.cfg"
    )
    runs {
        register("client") {
            client()
            // Comma-separated list of namespaces to load gametests from. Empty = all namespaces.
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", project.property("mod_id").toString())
        }

        register("data") {
            data()
            programArguments.addAll(
                "--mod", project.property("mod_id").toString(),
                "--all",
                "--output", file("src/generated/resources/").absolutePath,
                "--existing", file("src/main/resources/").absolutePath,
                "--existing", file("src/generated/resources/").absolutePath,
                "--existing-mod", "lodestone"
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create("${property("mod_id")}") {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets {
    main {
        resources.srcDir("src/generated/resources")
    }
}

repositories {
    mavenLocal()

    maven {
        name = "Modrinth"
        url = uri("https://api.modrinth.com/maven")
    }

    maven {
        name = "Illusive Soulworks maven"
        url = uri("https://maven.theillusivec4.top/")
    }

    maven { url = uri("https://maven.kosmx.dev/") }

    maven { url = uri("https://maven.minecraftforge.net/") }
    maven { url = uri("https://maven.blamejared.com") }
}

dependencies {
    implementation("net.neoforged:neoforge:${"neo_version"()}")

    implementation("maven.modrinth:fusion-connected-textures:${property("fusion_version")}-neoforge-mc${property("minecraft_version")}")
    implementation("com.github.glitchfiend:TerraBlender-neoforge:${property("minecraft_version")}-${property("terrablender_version")}")
    implementation("dev.kosmx.player-anim:player-animation-lib-forge:${property("player_anim_version")}")

    runtimeOnly("maven.modrinth:ferrite-core:7.0.2-neoforge")
    runtimeOnly("maven.modrinth:badpackets:neo-0.8.2")
    runtimeOnly("maven.modrinth:wthit:neo-12.10.0")
    implementation("maven.modrinth:emi:1.1.22+1.21.1+neoforge")

    compileOnlyApi("top.theillusivec4.curios:curios-neoforge:${property("curios_version")}:api")
    runtimeOnly("top.theillusivec4.curios:curios-neoforge:${property("curios_version")}")

}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val replaceProperties = mapOf(
        "minecraft_version" to project.findProperty("minecraft_version") as String,
        "minecraft_version_range" to project.findProperty("minecraft_version_range") as String,
        "neo_version" to project.findProperty("neo_version") as String,
        "neo_version_range" to project.findProperty("neo_version_range") as String,
        "loader_version_range" to project.findProperty("loader_version_range") as String,
        "mod_id" to project.findProperty("mod_id") as String,
        "mod_name" to project.findProperty("mod_name") as String,
        "mod_license" to project.findProperty("mod_license") as String,
        "mod_version" to project.findProperty("mod_version") as String,
        "mod_authors" to project.findProperty("mod_authors") as String,
        "mod_description" to project.findProperty("mod_description") as String,
        "fusion_version" to project.findProperty("fusion_version") as String,
        "terrablender_version" to project.findProperty("terrablender_version") as String,
        "player_anim_version" to project.findProperty("player_anim_version") as String,
        "curios_version" to project.findProperty("curios_version") as String
    )
    inputs.properties(replaceProperties)
    expand(replaceProperties)

    // Exclude .java files or any other files that shouldn't have template expansion
    filesMatching("**/*.java") {
        exclude()
    }

    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

sourceSets["main"].resources.srcDir(generateModMetadata)
neoForge.ideSyncTask(generateModMetadata)

java {
    withJavadocJar()
    withSourcesJar()
}

idea {
    module {
        for (fileName in listOf("run", "out", "logs")) {
            excludeDirs.add(file(fileName))
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>("mod") {
            artifactId = "archives_base_name"()
            groupId = "mod_group_id"()
            version = "mod_version"()
            pom.packaging = "jar"

            artifact(tasks.jar)
        }
    }

    repositories {
        if (System.getenv("MAVEN_USERNAME") != null && System.getenv("MAVEN_PASSWORD") != null) {
            maven {
                name = "StardustModding"
                url = uri("https://maven.stardustmodding.org/public-snapshots/")

                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }

        mavenLocal()
    }
}

operator fun String.invoke(): String =
    rootProject.ext[this] as? String ?: throw IllegalStateException("Property $this is not defined")
