this is how you start a git on the computer
open cmd -> write cd then drag the desierd folder to the terminal and hit enter
-> git init
-> git remote add origin https://github.com/oronkam12/LaddersAndSnakes.git (to connect to the repository)
-> git pull origin main (to pull the project)
-> git add . (to add all files changed)
-> git commit -m "message" to commit changes
--> git push -u origin main (to upload files to the repository)

#steps on how to upload files Importent!
1. git pull origin main
2. git add . (only if you want to load all files)!
3. git status (will color in green all files thats are going to be uploaded)
4. git commit -m "message of what changed"
5. git push -u origin main

git remote add origin https://github.com/oronkam12/LaddersAndSnakes.git
git branch -M main
git push -u origin main



#inishialize: 
echo "# LaddersAndSnakes" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/oronkam12/LaddersAndSnakes.git
git push -u origin main