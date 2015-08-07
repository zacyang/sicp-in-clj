(ns sicp.ch4.repl
  (:require [sicp.ch4.setup :as setup] 
            [sicp.ch4.core :as core]))

(def INPUT-PROMPT ";;; Eval input => ")

(def OUTPUT-PROMPT ";;; Eval value => ")

(defn prompt-for-input 
  [str]
  (println "")
  (println str)
  (println ""))

(defn announce-output 
  [str]
  (println "")
  (println str)
  (println ""))

(defn user-print
  "print compound procedure to avoid conflict and confusions."
  [obj]
  (if (core/compound-procedure? obj)
    (println (list 'compound-procedure
                   (core/procedure-parameters obj) 
                   (core/procedure-body obj)
                   'env
                   ))
    (println obj)))

(defn driver-loop
  []
  (prompt-for-input INPUT-PROMPT)
  (let [input (read)
        output (core/EVAL input the-global-environment)]
    (announce-output OUTPUT-PROMPT)
    (user-print output))

  (driver-loop)
)

