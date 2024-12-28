# android-aar-poc
Proof of concept to generate, publish, and consume aars.

This app has theree modules

* :**module_a**: This is a module that doesn't depend on any other internal modules.
* :**module_b**: This module depends on module_a
* :**app**: This is a regular android app that just uses the code from other two android library modules

Publish steps have to be run for each module that has changes before they can be consumed down the dependency chain.
ex: If we are changing code in module_a, it has to be changed and published before using in the module_b or app modules. Changes in module_a may not need changes in module_b if there are no contractual changes. 
However, for smaller size of teams and modules, it may easy to have every module have the same version or have some sort of peridic (monthly / quarterly) alignment of the modules so that the version management doesn't become a pain.

## Playing with this sample
You will need your github username, and a personal github access token (classic) to test this sample. The repo is already setup with Krishna's personal token. So you will need to just put these in your `local.properties` if you are testing this locally.

gh-packages-username=`your_user_name`

gh-packages-token=`your_token`

## Publishing module updates
Use the Publish module workflow and select a module that needs to be published. This doesn't have automatic version updates. So go ahead and update the version in the module specific build.gradle for the publishing artifact before running the publish step.

## Fetching and using published artifacts
The settings.gradle is already setup with most of what is required to set this up. As long as you used the instructors above to set up the username and password in local.properties, you'll only need to update the library artifact version to the latest version that you may have published.

