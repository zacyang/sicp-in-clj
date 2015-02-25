(ns sicp-in-clj.rational-test
  (:require [clojure.test :refer :all]
            [sicp-in-clj.rational :refer :all]))
(def fn-under-test make-rat)

(deftest basic-rational-structure-test
  (testing "should be able to construct a rational number"
    (is (= (fn-under-test 1 2) '(1 2)))
  (testing "should be able to get number from a rational number"
    (is (= (number (fn-under-test 1 2)) 1)))
  (testing "should be able to get denom from a rational number"
    (is (= (denom (fn-under-test 1 2)) 2)))
))

(deftest sicp-2-1-handle-negative-rat
  (testing "when rat is pos, then number and denom are all pos"
    (is (pos? (number (fn-under-test 2 3))))
    (is (pos? (denom (fn-under-test 2 3))))
    
    (is (pos? (number (fn-under-test -2 -3))))
    (is (pos? (denom (fn-under-test -2 -3))))
    )

  (testing "when rat is neg, then only number should be neg"
    (is (neg? (number (fn-under-test 2 -3))))
    (is (pos? (denom (fn-under-test 2 -3))))
    
    (is (neg? (number (fn-under-test -2 3))))
    (is (pos? (denom (fn-under-test -2 3))))
    )
)
