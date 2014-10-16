if ! [ -d "classes/" ]; then
	mkdir "classes"
fi

rm -f "classes/android.class"

javac -d "classes" "android.java"
if ! [ -f "classes/android.class" ]; then
	line
fi