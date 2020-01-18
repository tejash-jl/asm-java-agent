package com.sahajsoft.javaagent;

import java.lang.instrument.Instrumentation;

public class ASMAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[AGENT] Attaching the agent to the application");
        String classNameToTransform = "java/net/Socket";
        String methodNameToIntercept = "connect";
        ASMTransformer transformer = new ASMTransformer(classNameToTransform, methodNameToIntercept);
        inst.addTransformer(transformer);
    }
}
