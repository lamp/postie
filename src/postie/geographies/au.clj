(ns postie.geographies.au
  (:require [malli.generator :as g]
            [clojure.string :as string]
            [postie.geographies.common :refer [preprocess postcode-patterns]]))

(defn valid?
  [postcode]
  (->> postcode
       preprocess
       (re-matches (:au postcode-patterns))
       some?))

(defn format-postcode [postcode]
  (let [sanitised-postcode (preprocess postcode)]
    (if (valid? sanitised-postcode)
      sanitised-postcode
      false)))

(def generator
  (g/generator [:re (get postcode-patterns :au)]))

