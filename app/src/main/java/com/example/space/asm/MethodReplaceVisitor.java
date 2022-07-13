package com.example.space.asm;

import org.objectweb.asm.ClassVisitor;

public class MethodReplaceVisitor extends ClassVisitor {
    public MethodReplaceVisitor(int api) {
        super(api);
    }

    public MethodReplaceVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }
}
