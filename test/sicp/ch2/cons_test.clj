(ns sicp.ch2.cons-test
  (:refer-clojure :exclude [cons])
  (:use [clojure.test :refer :all]
        [sicp.ch2.cons :refer :all]))

(def church-100 (reduce church-plus (take 100 (repeat one))))

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
    (is (== (cons-multi-regular 1 2 ) 18))
    (is (== (cons-multi-regular 3 2 ) 72))
    (is (== (cons-multi-regular 0 0) 1))
    (is (== (cons-multi-regular 1 0) 2))
    (is (== (cons-multi-regular 0 1) 3))
    (is (== (cons-multi-regular 1 1) 6))
    
    (is (= (car-multi-regular (cons-multi-regular 5 7)) 5))
    (is (= (cdr-multi-regular (cons-multi-regular 5 7)) 7))


    (is (= (cons-car (cons-multi 5 7)) 5))
    (is (= (cons-cdr (cons-multi 5 7)) 7))
    (is (== (value-of (cons-multi 3 2)) 72))

))

(deftest church-2-int-coverting
  (testing "covert church to in"
    (is (= (church->int church-zero) 0))
    (is (= (church->int one) 1))
    (is (= (church->int two) 2))
    (is (= (church->int church-100) 100))
    ))


(deftest int-2-church-coverting
  (testing "covert int to church"
    (is (= (int->church 0) church-zero))
    (is (= (int->church 1) one))
    (is (= (int->church 2) two))
    (is (= (int->church 100) church-100))
))
