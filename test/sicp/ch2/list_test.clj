(ns sicp.ch2.list-test
    (:use [clojure.test :refer :all]
        [sicp.ch2.list :refer :all]))

(deftest basic-list-ref-test
  (testing "should return first element when there's only one element in the col"
    (is (list-ref [1] 0) 1))
  (testing "should return 2nd element when there are two elements in the col"
    (is (list-ref [1 2] 1) 2))
)

(deftest basic-list-length-test
  (testing "should return 0 when list is null"
    (is (list-length nil) 0))
  (testing "should return 0 when list contains zero element"
    (is (list-length []) 0))
  (testing "should return 1 when list contains one element"
    (is (list-length [1]) 1))
)

(deftest basic-list-append-test
  (testing "should return empty when first list is nil"
    (is (list-append nil [1 2 3]) '()))
  (testing "should return empty when both lists are empty"
    (is (list-append '() '()) '()))
)

(deftest basic-last-pair-test-2-17
  (testing "should thrown exception when col is empty"
    (is (thrown? IllegalArgumentException   (last-pair nil))))
  (testing "should return first elment when col has ONLY one element"
    (is (last-pair '(1)) 1))
  (testing "should return first elment when col has multi elements"
    (is (last-pair '(3 1 32)) 3))
)

(deftest reverse-basic-test-2-17
  (testing "should return reversed order col"
    (is (col-reverse [1 2 3]) [3 2 1])))
