(ns sicp-in-clj.rational)

(defn make-rat [number denom]
  "construct a rational number"
  (list number denom)
)

(defn number [rational]
  "get the number of a rational number"
  (first rational))

(defn denom [rational]
  "get the demon of a rational number"
  (last rational))

(defn print-rat [rational]
  (println (number rational) "/" (denom rational)))
