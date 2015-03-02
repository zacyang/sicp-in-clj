(ns sicp.ch2.cons-test
  (:refer-clojure :exclude [cons])
  (:use [clojure.test :refer :all]
        [sicp.ch2.cons :refer :all]))

(deftest basic-cons-test
  (testing "baisc opts"
    (is (fn? (cons 1 2) ))
    (is (car (cons 1 2)) 1)
    (is (cdr (cons 1 2)) 2)
))

(deftest sicp-24
  (testing "given any x and y should be able get x"
    (is (= (cdr24 (cons24 1 2)) 2))
    ))

(deftest sicp-25
  (testing "a^2 multiply b^3 should be able to represent any integer greater than 1. And we should be able to get a and b based on a given value x."
    (is (= (cons-car (cons-multi 1 3)) 1))
    (is (= (cons-cdr (cons-multi 1 3)) 3))
    (is (= (value-of (cons-multi 1 3)) 24))
))

