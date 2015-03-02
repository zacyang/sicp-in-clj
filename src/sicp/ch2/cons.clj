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

;;; 2.5
(defn expt [base n]
  (bit-shift-left base (- n 1)))

(defn cons-multi-regular [x y]
  (* (expt 2 x)
     (expt 3 y)))

(defn car-multi-regular [z]
  "in order to get x, we need caculate how many 2^x and 3^y will suit the case. er...."
  
  )

;;; again, thinking another way around, DONT BE FOOLED BY THE question!!!
(defn cons-multi [x y]
  (fn [pick] (pick x y)))

(defn cons-car [z]
  (z (fn pick-x [x y] x)))

(defn cons-cdr [z]
  (z (fn pick-y [x y] y)))

(defn value-of [z]
  (z (fn pick-value [x y]
       (* (expt 2 x)
          (expt 3 y)))))




