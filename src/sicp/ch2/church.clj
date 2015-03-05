(ns sicp.ch2.church)


;;; 2.6
;;; the fllowing presentatation is call Church Number by Alonzo Church
;;; consider a system, there's literal number exists (only non-neg)
;;; we car def 0 and plus 1 as:
(defn church-zero [f]
  (fn [x] x))

(defn add-one [n]
  (fn [f]
    (fn [x] (f ((n f) x)))))
;;; please design one and two, YOU CANT USE my-zero and add-one

(defn one [f]
  (fn [x] (f x)))

(defn two [f]
  (fn [x] (f (f x))))

(defn church-plus [right left]
  (fn [f] (fn [x]
            ((right f) ((left f) x)))))

(defn church-mult [right left]
  (fn [f] (left (right f)))
  )

(defn show-fn [x]
  (println :1))

;;;
;;;
;;; ADDTIONAL defn function for converting church->integer and interger->church

(defn church->int [f]
  ((f (fn [x] (inc x))) 0)
  )

(defn int->church [n]
  (if (zero? n) church-zero
      (reduce church-plus (take n (repeat one)))))

