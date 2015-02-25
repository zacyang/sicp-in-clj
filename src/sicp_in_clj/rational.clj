(ns sicp-in-clj.rational
  "sicp chap 2.1")

(defn- make-rat-stupid-v1 [number denom]
  "construct a rational number"
  (list number denom)
)

;v2
(defn- make-rat-v2 [number denom]
 "construct a rational number"
 (cond (and (pos? number) (pos? denom)) (make-rat-stupid-v1 number denom)
       (and (neg? number) (neg? denom)) (make-rat-stupid-v1 (- number) (- denom))
       :else (make-rat-stupid-v1 (- (Math/abs number)) (Math/abs denom))
       ))

;v3
(defn make-rat [number denom]
 "construct a rational number"
 (let [g (gcd number denom)
       simplified-number (/ number g)
       simplified-denom (/ denom g)]
   (make-rat-v2 simplified-number simplified-denom)
))

;readable v4
(defn- simplifier [number against]
  (let [g (gcd number against)]
    (/ number g)))

(defn- neg-pos-handle [number denom cons-fn]
  (cond (and (pos? number) (pos? denom)) (cons-fn number denom)
        (and (neg? number) (neg? denom)) (cons-fn (- number) (- denom))
        :else (cons-fn (- (Math/abs number)) (Math/abs denom))
       )
)

(defn readable-make-rat [n d]
  (neg-pos-handle (simplifier n d) (simplifier d n) make-rat-stupid-v1))


(defn number [rational]
  "get the number of a rational number"
  (first rational))

(defn denom [rational]
  "get the demon of a rational number"
  (last rational))

(defn print-rat [rational]
  (println (number rational) "/" (denom rational)))

(defn- gcd "(gcd a b) returns the greatest common divisor of a and b" [a b]
  (if (or (not (integer? a)) (not (integer? b)))
    (throw (IllegalArgumentException. "gcd requires two integers"))  
    (loop [a (Math/abs a) b (Math/abs b)]
      (if (zero? b) a,
	  (recur b (mod a b))))))
