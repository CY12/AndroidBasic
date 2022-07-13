package com.example.space.asm;

import com.example.space.utils.AsmUtils;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.FileInputStream;
import java.util.List;

public class TreeApiTest {
    public static void main(String[] args) throws Exception {
//        Class clazz = InjectUser.class;
//        String clazzFilePath = AsmUtils.getClassFilePath(clazz);
//        ClassReader classReader = new ClassReader(new FileInputStream(clazzFilePath));
//        ClassNode classNode = new ClassNode(Opcodes.ASM5);
//        classReader.accept(classNode, 0);
//
//        List<MethodNode> methods = classNode.methods;
//        List<FieldNode> fields = classNode.fields;
//
//        System.out.println("methods:");
//        for (MethodNode methodNode : methods) {
//            System.out.println(methodNode.name + ", " + methodNode.desc);
//        }
//
//        System.out.println("fields:");
//        for (FieldNode fieldNode : fields) {
//            System.out.println(fieldNode.name + ", " + fieldNode.desc);
//        }
        Class clazz = InjectUser.class;
        String clazzFilePath = AsmUtils.getClassFilePath(clazz);
        ClassReader classReader = new ClassReader(new FileInputStream(clazzFilePath));
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5) {
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("visit field:" + name + " , desc = " + descriptor);
                return super.visitField(access, name, descriptor, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("visit method:" + name + " , desc = " + descriptor);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };
        classReader.accept(classVisitor, 0);

    }

}
