import net.civmc.civgradle.common.util.civRepo

plugins {
	`java-library`
	`maven-publish`
	id("net.civmc.civgradle.plugin") version "1.0.0-SNAPSHOT"
}

allprojects {
	group = "net.civmc.namecolors"
	version = "2.0.0-SNAPSHOT"
	description = "NameColors"
}

subprojects {
	apply(plugin = "net.civmc.civgradle.plugin")
	apply(plugin = "java-library")
	apply(plugin = "maven-publish")

	java {
		toolchain {
			languageVersion.set(JavaLanguageVersion.of(17))
		}
	}

	repositories {
		mavenCentral()

		maven("https://papermc.io/repo/repository/maven-public/")
		maven("https://repo.codemc.io/repository/maven-public/")
		maven("https://repo.kryptonmc.org/releases")

		civRepo("CivMC/CivModCore")
		civRepo("CivMC/NameLayer")
		civRepo("CivMC/CivChat2")
	}

	publishing {
		repositories {
			maven {
				name = "GitHubPackages"
				url = uri("https://maven.pkg.github.com/CivMC/NameColors")
				credentials {
					username = System.getenv("GITHUB_ACTOR")
					password = System.getenv("GITHUB_TOKEN")
				}
			}
		}
		publications {
			register<MavenPublication>("gpr") {
				from(components["java"])
			}
		}
	}
}
