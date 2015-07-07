(ns sicp.ch3.arrays)

;;; 3.16 on the book, and this is the wrong version

(defn count-pairs [x]
  (if (not (list? x))
    0
    (+ (count-pairs (first x))
       (count-pairs (rest x))
       1)))


