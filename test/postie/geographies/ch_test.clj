(ns postie.geographies.ch-test
  (:require [clojure.test :refer [deftest testing is]]
            [postie.geographies.ch :as sut]
            [malli.generator :as g]))

(deftest valid?-test
  (testing "valid postcode"
    (is (true? (sut/valid? "1204"))))
  (testing "valid but badly formatted postcode"
    (is (true? (sut/valid? "
                           1204      "))))
  (testing "an invalid postcode"
    (is (false? (sut/valid? "ABCD")))))

(deftest format-postcode-test
  (testing "with a valid postcode"
    (is (= "1204"
           (sut/format-postcode "1204")))))

(deftest generator-test
  (is (sut/valid? (g/generate sut/generator))))
