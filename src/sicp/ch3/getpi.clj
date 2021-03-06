(ns sicp.ch3.getpi
  (:require [sicp.ch2.rational :as r :only [gcd]])
)

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

(defn- randome-in-range [low high]
  "get a rand num in a range"
  (let [r (- high low)]
    (+ low (rand-int r))))

(defn- pow [x] (Math/pow x 2))
(defn abs [x] (Math/abs x))
(defn- if-point-is-in-the-area-of [x y cycle]
       (<= 
        (+
         (pow (- x (cycle :x)))
         (pow (- y (cycle :y))))
        (pow (cycle :len))
        ))



(defn estimate-inegral [x1 x2 y1 y2 trials]
  (defn get-cycle-len [](min (abs (- x1 x2)) (abs (- y1 y2))))
  (defn try-allocate-rand-point-in-area[]
    (if-point-is-in-the-area-of (randome-in-range x1 x2)  (randome-in-range y1 y2) 
                                   {:x (/ (- x1 x2) 2)
                                    :y (/ (- y1 y2) 2)
                                    :len (get-cycle-len)})
    )

  (let [hit-rate (monte-carlo trials try-allocate-rand-point-in-area)
        rectangular-area (+ (pow (- x1 x2)) (pow (- y1 y2)))
        ]
    (println "eara :" rectangular-area)
    (println "hit-rate:" hit-rate)
    (/ (* rectangular-area hit-rate) (get-cycle-len))
    ))

(defn do-stuff [x]
  (def result (atom 0))
  (def evaled (atom false))
  
  (cond (and (not evaled) (= x 0))
        (do (swap! evaled (fn [_] true))
            (swap! result (fn [_] 0))
            )
        ))
