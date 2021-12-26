(ns postie.geographies.common
  (:require [clojure.string :as string]))

(def postcode-patterns
  {:at :ch
   :be :ch
   :ch #"^[1-9][0-9]{3}$"
   :de #"^([0]{1}[1-9]{1}|[1-9]{1}[0-9]{1})[0-9]{3}$"
   :dk :ch
   :fr :de
   :gb {:out-code #"^[A-Z]{1,2}([1-9][0-9A-HJKMNPR-Y]?|0[A-HJKMNPR-Y]?)$"
        :incode #"^[0-9][A-HJLNP-Z]{2}$"
        :complete #"^[A-Z]{1,2}([1-9][0-9A-HJKMNPR-Y]?|0[A-HJKMNPR-Y]?) [0-9][A-HJLNP-Z]{2}$"}
   :lu :ch
   :nl #"^[1-9][0-9]{3} (S[BCE-RT-Z]|[A-RT-Z][A-Z])$"
   :no #"^[0-9]{4}$"
   :nz :au
   :pl #"^(\d\d)[^\w]*(\d\d\d)$"
   :ro #"^[0-9]{6}$"
   :se #"/^[1-9]{1}[0-9]{4}$/"
   :us #"^[0-9]{5}"
   :za :au})

(defn preprocess [postcode]
  (-> postcode
      string/trim
      string/trim-newline
      (string/replace #"\n|\s|\r" "")))


