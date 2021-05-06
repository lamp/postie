(ns postie.ca-postcode-test
  (:require [clojure.test :refer [deftest is testing]]
            [postie.core :as sut]))

(deftest valid-ca-postcode?-test
  (testing "when given a valid postcode"
    (is (true? (sut/valid-ca-postcode? "K1A 0A1"))))

  (testing "it handles badly formatted but valid postcodes"
    (let [valid? (sut/valid-ca-postcode? "k1a0a1")]
      (is (true? valid?))))

  (testing "it handles lower case postcodes"
    (is (true? (sut/valid-ca-postcode? "k1a 0a1"))))

  (testing "when given an invalid postcode"
    (is (false? (sut/valid-ca-postcode? "KAA 01A")))))

(deftest format-ca-postcode-test
  (testing "It returns the formatted postcode when it is valid"
    (is (= "K1A 0A1"
          (sut/format-ca-postcode "k1a 0A1"))))
  (testing "It handles badly formatted postcodes"
    (is (= "K1A 0A1"
           (sut/format-ca-postcode "k1a0a1")))))

(deftest generate-ca-postcode-test
  (testing))
