(ns sicp.ch2.map-test
  (:use [clojure.test :refer :all]
        [sicp.ch2.map]))
(deftest basic-my-map-test
  (testing "map function to col"
    (is (= (my-map inc [1 2 3]) [2 3 4]))))

(deftest square-list-test
  (testing "should return a square list"
    (is (=  (square-list '(1 2 3)) '(1 4 9)))))

(deftest tree-opts-test
  (testing "should return leaves count"
    (def test-tree (list (list 1 2) (list 3 4)))
    (is (count-leaves test-tree) 4)
    (is (count-leaves  (list test-tree test-tree)) 8)
    
    (is (count-leaves-cp test-tree) 4)
    (is (count-leaves-cp  (list test-tree test-tree)) 8)
))

(deftest tree-square-test
  (testing "basic test"
    (is (square-tree '(1)) '(1))))
