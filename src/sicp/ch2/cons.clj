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

(defn cons24 [x y]
  (fn [m] (m x y)))

(defn car24 [z]
  (z (fn [p q] p)))

(defn cdr24 [z]
  (z (fn [p q] q)))
