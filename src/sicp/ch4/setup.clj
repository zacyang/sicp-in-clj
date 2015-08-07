(ns sicp.ch4.setup
  (:require [sicp.ch4.core :as core]  ))

(def apply-in-underlying-clj apply)

(defn primitive-procesdure?
  [proc]
  (tagged-list? proc 'primitive))

(defn primitive-implementation
  [proc]
  (nth proc 3))

(def primitive-procedures 
  (list (list 'car first)
        (list 'cdr rest)
        (list 'cons cons)
        (list 'null? nil?)
        ))

(def primitive-procedure-names
  (map first primitive-procedures))

(def primitive-procedure-objects
  (map (fn [proc] (list 'primitive (second proc)))
       primitive-procedures))

(defn apply-primitive-procedure 
  [proc args]
  (apply-in-underlying-clj
   (primitive-implementation proc) args))

(defn setup-environment
  []
  (let [initial-env (extend-enviroment primitive-procedure-names
                                       primitive-procedure-objects
                                       the-empty-environment)]
    (set-variable-value! 'true true initial-env)
    (set-variable-value! 'false false initial-env)))

(def the-global-environment (setup-environment))


