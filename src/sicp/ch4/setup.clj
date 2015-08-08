(ns sicp.ch4.setup
  (:require [sicp.ch4.core :as core]  ))

(def apply-in-underlying-clj apply)

(defn primitive-procesdure?
  [proc]
  (core/tagged-list? proc 'primitive))

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
  (let [initial-env (core/extend-enviroment primitive-procedure-names
                                       primitive-procedure-objects
                                       core/the-empty-environment)]
    (->> initial-env
         (core/define-variable! 'true true)
         (core/define-variable! 'false false))

))

(def the-global-environment (setup-environment))


