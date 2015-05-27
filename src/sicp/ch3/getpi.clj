(ns sicp.ch3.getpi)

;;; examples on the book

;;; get pi first version


;; (defn rand-update! [x]
;;   (rand-int 10000)
;;   )

;; (defn rand-num []
;;   (let [x (atom (rand-int 10000))]
;;     (swap! x (fn [_] (rand-int 10000)))
;;     x ))



;; (defn monte-carlo [trails experiment]
;;   (defn- iter [trials-remaining trials-passed]
;;     (cond 
;;       (zero? trials-remaining) (/ trials-passed trials-remaining)
;;       (experiment) (iter (dec trials-remaining) (inc trials-passed))
      ;; :else (iter (dec trials-remaining) trials-passed))))



(defn cesaro-test
  (= (r/gcd (rand-num) (rand-num))
     1))


(defn estimate-pi [trials]
  (Math/sqrt (/ 6 (mote-carlo trials cesaro-test))))
