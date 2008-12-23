/*
 * Copyright (c) 2008  Dave Ray <daveray@gmail.com>
 *
 * Created on Dec 18, 2008
 */
package org.jsoar.kernel;

import org.jsoar.util.properties.PropertyKey;

/**
 * Declaration of per-agent Soar configuration properties.
 * 
 * <p><b>Not used yet</b>
 * 
 * @author ray
 */
public class SoarProperties
{
    // TODO Reinforcement Learning
    // TODO Exploration
    
    /**
     * agent.h:740:waitsnc
     * @see Decider
     */
    public static final PropertyKey<Boolean> WAITSNC = PropertyKey.builder("waitsnc", Boolean.class).defaultValue(false).build();
    
    /**
     * <p>gsysparams.h::MAX_NIL_OUTPUT_CYCLES_SYSPARAM
     * @see DecisionCycle
     */
    public static final PropertyKey<Integer> MAX_NIL_OUTPUT_CYCLES = PropertyKey.builder("max-nil-output-cycles", Integer.class).defaultValue(15).build();
    
    /**
     * <p>gsysparam.h:MAX_ELABORATIONS_SYSPARAM
     * 
     * @see Consistency
     */
    public static final PropertyKey<Integer> MAX_ELABORATIONS = PropertyKey.builder("max-elaborations", Integer.class).defaultValue(100).build();
    
    /**
     * <p>gsysparam.h:164:MAX_GOAL_DEPTH
     * <p>Defaults to 100 in init_soar()
     * @see Decider
     */
    public static final PropertyKey<Integer> MAX_GOAL_DEPTH = PropertyKey.builder("max-goal-depth", Integer.class).defaultValue(100).build();

    
    /**
     * <p>gsysparam.h:179:CHUNK_THROUGH_LOCAL_NEGATIONS_SYSPARAM
     * <p>Defaults to true in init_soar()
     */
    public static final PropertyKey<Boolean> CHUNK_THROUGH_LOCAL_NEGATIONS = PropertyKey.builder("chunk-through-local-negations", Boolean.class).defaultValue(true).build();
    
    /**
     * <p>gsysparam.h:143:USE_LONG_CHUNK_NAMES
     * <p>Defaults to true in init_soar() 
     */
    public static final PropertyKey<Boolean> USE_LONG_CHUNK_NAMES = PropertyKey.builder("use-long-chunk-names", Boolean.class).defaultValue(true).build();
    
    /**
     * <p>agent.h:535:chunk_name_prefix
     * <p>Defaults to "chunk" in init_soar()
     */
    public static final PropertyKey<String> CHUNK_NAME_PREFIX = PropertyKey.builder("chunk-name-prefix", String.class).defaultValue("chunk").build();
    
    /**
     * <p>gsysparam.h:123:LEARNING_ON_SYSPARAM
     * <p>Defaults to false in init_soar()
     */
    public static final PropertyKey<Boolean> LEARNING_ON = PropertyKey.builder("learning-on", Boolean.class).defaultValue(false).build();
    
    /**
     * <p>gsysparam.h:126:LEARNING_ALL_GOALS_SYSPARAM
     * <p>Defaults to true in init_soar()
     */
    public static final PropertyKey<Boolean> LEARNING_ALL_GOALS = PropertyKey.builder("learning-all-goals", Boolean.class).defaultValue(true).build();
    /**
     * <p>gsysparam.h:125:LEARNING_EXCEPT_SYSPARAM
     * <p>Defaults to false in init_soar
     */
    public static final PropertyKey<Boolean> LEARNING_EXCEPT = PropertyKey.builder("learning-except", Boolean.class).defaultValue(false).build();
    
    /**
     * <p>gsysparam.h:124:LEARNING_ONLY_SYSPARAM
     * <p>Defaults to false in init_soar
     */
    public static final PropertyKey<Boolean> LEARNING_ONLY = PropertyKey.builder("learning-only", Boolean.class).defaultValue(false).build();
    
    /**
     * <p>gsysparam.h:140:EXPLAIN_SYSPARAM
     * <p>Defaults to false in init_soar()
     */
    public static final PropertyKey<Boolean> EXPLAIN = PropertyKey.builder("explain", Boolean.class).defaultValue(false).build();
    
    /**
     * agent.h:687:o_support_calculation_type
     */
    public static final PropertyKey<Integer> O_SUPPORT_MODE = PropertyKey.builder("o-support-mode", Integer.class).defaultValue(4).build();

}
