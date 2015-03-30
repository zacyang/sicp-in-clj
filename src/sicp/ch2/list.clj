(ns sicp.ch2.list)

;;; ops to a list
;;;  1. if n = 0, list-ref should return car
;;;  2. else, list-ref returns cdr's (n-1)th element

(defn list-ref [items n]
  (if (zero? n)
    (first items)
    (list-ref (rest items) (dec n)))
)
