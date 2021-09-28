# Amazon Web Services SDK Plugin

[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/aws-java-sdk.svg)](https://plugins.jenkins.io/aws-java-sdk)
[![GitHub release](https://img.shields.io/github/v/release/jenkinsci/aws-java-sdk-plugin.svg?label=release)](https://github.com/jenkinsci/aws-java-sdk-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/aws-java-sdk.svg?color=blue)](https://plugins.jenkins.io/aws-java-sdk)

This plugin provides the [AWS SDK for Java](https://aws.amazon.com/sdk-for-java/) as a library to be used by other plugins. It follows the same versioning as the AWS SDK itself.

Commonly used modules have their own plugins, less used modules are in the `aws-java-sdk` plugin.

## Adding a new plugin

If you need to use an API that is not yet published as its own plugin, feel free to submit a pull request to create a plugin for it. This will avoid pulling the all-in-one `aws-java-sdk` plugin.

* Create a new directory `aws-java-sdk-<name>`. The name should be identical to the aws sdk module.
* Create `pom.xml`.
** Depend on `com.amazonaws:aws-java-sdk-<name>`. Exclude all transitive dependencies.
** Transitive dependencies should be replaced by their equivalent plugin dependency. Most APIs only depend on `aws-java-sdk-core` and `jmespath-java` (called `aws-java-sdk-jmespath` in this project for clarity in the Jenkins ecosystem).
* Create `src/main/resource/index.jelly`. Look at existing modules and adapt it.
* Add the module to the root `pom.xml`.
* Add the plugin dependency to `aws-java-sdk` and exclude the module from transitive dependencies.
