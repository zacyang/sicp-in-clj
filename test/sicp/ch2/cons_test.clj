(ns sicp.ch2.cons-test
  (:refer-clojure :exclude [cons])
  (:use [clojure.test :refer :all]
        [sicp.ch2.cons :refer :all]))

(deftest basic-cons-test
  (testing ""
    (is (fn? (cons 1 2) ))
    (is (car (cons 1 2)) 1)
    (is (cdr (cons 1 2)) 2)
))
