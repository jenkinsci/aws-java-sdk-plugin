# Amazon Web Services SDK Plugin

[![Jenkins Plugin](https://img.shields.io/jenkins/plugin/v/aws-java-sdk.svg)](https://plugins.jenkins.io/aws-java-sdk)
[![GitHub release](https://img.shields.io/github/v/release/jenkinsci/aws-java-sdk-plugin.svg?label=release)](https://github.com/jenkinsci/aws-java-sdk-plugin/releases/latest)
[![Jenkins Plugin Installs](https://img.shields.io/jenkins/plugin/i/aws-java-sdk.svg?color=blue)](https://plugins.jenkins.io/aws-java-sdk)

This plugin provides the [AWS SDK for Java](https://aws.amazon.com/sdk-for-java/) as a library to be used by other plugins. It follows the same versioning as the AWS SDK itself.

Commonly used modules have their own plugins, less used modules are in the `aws-java-sdk` plugin.

## Requesting new instance types

Some plugins, such as the [ec2-plugin](https://github.com/jenkinsci/ec2-plugin) rely on the AWS SDK to list available instance types. Updates of the AWS SDK come via this plugin.

There is a new release of the AWS SDK every day, and most updates are not relevant to most Jenkins users. 

As the current maintainer of this library plugin, I am not actively monitoring the [AWS SDK changelog](https://github.com/aws/aws-sdk-java/blob/master/CHANGELOG.md).

If you want to use a new instance type and it is not yet available through this plugin:
* look up in what version of the AWS SDK it has been introduced ([changelog](https://github.com/aws/aws-sdk-java/blob/master/CHANGELOG.md))
* find the latest dependabot pull request bumping the AWS SDK ([link](https://github.com/jenkinsci/aws-java-sdk-plugin/pulls?q=is:pr+is:open+sort:updated-desc+revision))
* ask for a merge and release after providing the instance type you are looking for, and the version of the AWS SDK it has been introduced in.

## Plugins
### aws-java-sdk-minimal
This plugins contains multiple modules.
These have been grouped together as `aws-java-sdk-core` needs some classes in the same classpath and the structured classloaders in Jenkins don't permit having them in different plugins. 

* aws-java-sdk-core
* aws-java-sdk-kms
* aws-java-sdk-s3
* aws-java-sdk-sts
* jmespath-java

### aws-java-sdk-*
Contains an individual AWS Java SDK module with the same name.

### aws-java-sdk
Contains all AWS Java SDK modules not already provided through a separate plugin. It depends on all other aws-java-sdk plugins and is very heavyweight.

## Adding a new plugin

If you need to use an API that is not yet published as its own plugin, feel free to submit a pull request to create a plugin for it. This will avoid pulling the all-in-one `aws-java-sdk` plugin.

* Create a new directory `aws-java-sdk-<name>`. The name should be identical to the aws sdk module.
* Create `pom.xml`.
  * Depend on `com.amazonaws:aws-java-sdk-<name>`. Exclude all transitive dependencies.
  * Transitive dependencies should be replaced by their equivalent plugin dependency. Most APIs only depend on `aws-java-sdk-core` and `jmespath-java` (both are part of the `aws-java-sdk-minimal` plugin).
* Create `src/main/resource/index.jelly`. Look at existing modules and adapt it.
* Add the module to the root `pom.xml`.
* Add the plugin dependency to `aws-java-sdk` and exclude the module from transitive dependencies.
