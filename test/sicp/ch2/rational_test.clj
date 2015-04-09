(ns sicp.ch2.rational-test
  (:use [clojure.test :refer :all]
            [sicp.ch2.rational :refer :all]))
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
    ))

(deftest gdc-hanlding
  (testing "should be able to get simplified representation when there is a GDC between number and nenom"
    (throw  (Exception))
    (is (= (fn-under-test 2 4) '(1 2)))
    (is (= (fn-under-test 3 5) '(3 5)))
    (is (= (fn-under-test 100 100) '(1 1)))
   
    ))
