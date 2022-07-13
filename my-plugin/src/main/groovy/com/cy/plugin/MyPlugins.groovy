package com.cy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugins implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("this is myPlugins")

    }
}