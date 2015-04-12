(ns sicp.ch2.map)

(defn my-map 
  [proc col]
  (if (empty? col)
    col
    (cons (proc (first col))
          (my-map proc (rest col)))))

;;; 2.21
(defn square-list [col]
  (map #(* % %) col))

;;; 2.22
;;; why it is reversed result?
(defn square-list-lr-version
  [col]
  (defn iter [things answer]
    (if (empty? things)
      answer
      ;; debugq
      (do (println "rest" (rest things))
          (println "answer :"(cons (#(* % %) (first things)) answer)) 
          (iter (rest things)
                (cons (#(* % %) (first things)) answer))
          )
     ))

  (iter col nil))
;;; answer : 1. cons to list will append the new element in reverse order ,that is (cons 1 '(2)) result in '(1 2) rather than '(2 1)

;;; 2.23 define a for-each

(defn for-each 
  [proc col]
  (if (not-empty col)
    (do (proc (first col))
        (proc (for-each proc (rest col)))))
