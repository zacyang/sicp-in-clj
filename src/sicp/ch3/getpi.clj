(ns sicp.ch3.getpi
  (:require [sicp.ch2.rational :as r :only [gcd]]))

;;; examples on the book

;;; get pi first version


(defn rand-update! [x]
  (rand-int 10000)
  )

(defn rand-num []
  (let [x (atom (rand-int 10000))]
    (swap! x (fn [_] (rand-int 10000)))
    @x ))



(defn monte-carlo [trials experiment]
  (defn- iter [trials-remaining trials-passed]
    (cond 
      (zero? trials-remaining) (/ trials-passed trials)
      (experiment) (iter (dec trials-remaining) (inc trials-passed))
      :else (iter (dec trials-remaining) trials-passed)))
  
  (iter trials 0)
)

(defn cesaro-test []
  (= (r/gcd (rand-num) (rand-num))
     1))

(defn estimate-pi [trials]
  (Math/sqrt (/ 6 (monte-carlo trials cesaro-test))))

;;; question remains, solve stack overflow
(defn rand-num-with [init-value]
  (let [x (atom init-value)]
    (swap! x (fn [_] (rand-int 10000)))
    @x ))


(defn rand-num-with-init-parameter 

  ([reset-cmd value]
   (if (= reset-cmd :reset)
     (rand-num-with value)
     :unspported
     ))

  ([generate-cmd]
   (if (= generate-cmd :generate)
     (rand-num-with (rand-int 10000))
     :unspported
     )
  
  ))


