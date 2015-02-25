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
(defn make-rat-v3 [number denom]
 "construct a rational number"
 (let [g (gcd number denom)
       simplified-number (/ number g)
       simplified-denom (/ denom g)]
   (make-rat-v2 simplified-number simplified-denom)
))

;v4 thinking another around
(defn- neg-pos-switch [rat]
  (let [number (number rat)
        denom (denom rat)]
    (neg-pos-handle number denom make-rat-stupid-v1)
))

(defn- simplifier [rat]
  (let [g (gcd (number rat) (denom rat))]
    (make-rat-stupid-v1 
     (/ (number rat) g)
     (/ (denom rat) g))))


(defn make-rat [n d]
  (-> (make-rat-stupid-v1 n d)
      simplifier
      neg-pos-switch))


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
