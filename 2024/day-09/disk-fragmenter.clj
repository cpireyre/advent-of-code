(require '[clojure.pprint :refer [pprint]])

(def digits {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7 \8 8 \9 9})

(defn parse [input]
  (let [disk-map (map digits input)
        map-block (fn [id size]
                    (repeat size
                            (if (even? id)
                              (/ id 2)
                              \.)))]
    (vec (apply concat (map-indexed map-block disk-map)))))

(defn dot? [c] (= \. c))

(defn find-swap-pos [disk]
  (when-let [i (first (keep-indexed (fn [i v] (when (dot? v) i)) disk))]
    (loop [j (dec (count disk))]
      (cond
        (<= j i) nil
        (not (dot? (nth disk j))) [i j]
        :else (recur (dec j))))))

(defn defrag-step [disk]
  (when-let [[left right] (find-swap-pos disk)]
    (when (< left right)
      (-> disk
          (assoc left (nth disk right))
          (assoc right \.)))))

(defn defrag [disk-map]
  (if-let [next-state (defrag-step disk-map)]
    (recur next-state)
    disk-map))

(defn calculate-checksum [defragged-disk]
  (apply + (map-indexed (fn [idx val]
                         (* idx val))
                       (remove dot? defragged-disk))))

(defn solve [filename]
  (-> filename
      slurp
      clojure.string/trim
      parse
      defrag
      calculate-checksum))

; (println (time (solve "example.txt")))
; (println (time (solve "puzzle-input.txt")))

(defn parse-blocks [input]
  (loop [disk (map vector (range (count input)) input)
         files []
         free []]
    (let [[index c] (first disk)]
      (if (empty? disk)
        {:files (reverse files) :free free}
        (recur (rest disk)
               (if (even? index) (conj files {:offset index :id (/ index 2) :size (digits c)}) files)
               (if (odd? index) (conj free {:offset index :size (digits c)}) free))))))

(defn defrag-step [disk]
  (let [{:keys [files free]} disk
        target (first files)
        big-enough (fn [block] 
                     (when
                       (<= (:size target) (:size block))
                       block))
        destination (first (keep big-enough free))]
    (if (nil? destination)
      disk
      {:files
        (-> files
            (dissoc target)
            (assoc {

       :free

(defn part-2 [filename]
  (-> filename
      slurp
      parse-blocks
      defrag-step))

(pprint (time (part-2 "example.txt")))
(time (part-2 "puzzle-input.txt"))
