(ns sicp.ch2.cons
  "lisp cons impl"
  (:refer-clojure :exclude [cons]))

(defn cons [x y]
  (defn dispatch [m]
    (cond 
      (= 0 m) x
      (= 1 m) y
      :else (throw (IllegalArgumentException. "Argument not 0 or 1 -- cons" m))))
 dispatch)

(defn car [z]
  (z 0))

(defn cdr [z]
  (z 1))
