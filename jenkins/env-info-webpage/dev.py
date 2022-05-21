from flask import Flask, render_template      

app = Flask(__name__)

@app.route("/")
def home():
    return render_template("dev.html")

@app.route("/salvador")
def salvador():
    return "Hello, Salvador dev testing"

if __name__ == "__main__":
    app.run(debug=True, host='0.0.0.0', port=3000)

