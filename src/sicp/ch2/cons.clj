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

(defn- num-divs [n d]
  (defn iter [x result]
    (if (zero? (rem x d)) 
      (iter (/ x d) (inc result))
      result))
  (iter n 0)
)

(defn car-multi-regular [z]
  (num-divs z 2))

(defn cdr-multi-regular [z]
  (num-divs z 3))




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

;;; 2.6
;;; consider a system, there's literal number exists (only non-neg)
;;; we car def 0 and plus 1 as:
(defn my-zero [f]
  (fn [x] x))

(defn add-one [n]
  (fn [f] 
    (fn [x] (f ((n f) x)))))
;;; please design one and two

