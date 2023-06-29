import math
import pygame
import random

pygame.init()
pygame.mixer.init()

screen_width = 700
screen_height = 500

screen = pygame.display.set_mode((screen_width, screen_height))

pygame.display.set_caption("Snake")

button = pygame.image.load("Assets/Button/button.png")
home_button = pygame.image.load("Assets/Button/home_button.png")
menu_button = pygame.image.load("Assets/Button/menu_button.png")
next_button = pygame.image.load("Assets/Button/next_button.png")
previous_button = pygame.image.load("Assets/Button/previous_button.png")
restart_button = pygame.image.load("Assets/Button/restart_button.png")
food_eat = pygame.mixer.Sound('Assets/Audio/food_eat.mp3')
button_click = pygame.mixer.Sound('Assets/Audio/button_click.mp3')

score = 0
high_score = 0
life = 3
state = 2
continued = True
selected_color = 0
colors = [(255, 0, 0), (0, 255, 0), (0, 0, 255), (255, 255, 0), (0, 255, 255), (255, 0, 255), (255, 165, 0),
          (128, 0, 128), (255, 192, 203)]

clock = pygame.time.Clock()
fps = 60


class Pause:
    def __init__(self):
        self.backButton = {"x": screen_width - 60,
                           "y": 10,
                           "width": 50,
                           "height": 50
                           }
        self.playButton = {"x": screen_width - 60,
                           "y": 70,
                           "width": 50,
                           "height": 50
                           }
        self.restartButton = {"x": screen_width - 60,
                              "y": 130,
                              "width": 50,
                              "height": 50
                              }

    def render(self):
        game_text = "PAUSED"
        font = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 45)
        game_text_render = font.render(game_text, True, (255, 255, 255))
        text_width, text_height = font.size(game_text)
        screen.blit(game_text_render, ((screen_width - text_width) / 2, (screen_height - text_height) / 2))
        screen.blit(pygame.transform.scale(home_button, (self.backButton["width"], self.backButton["height"])),
                    (self.backButton["x"], self.backButton["y"]))
        screen.blit(pygame.transform.scale(next_button, (self.playButton["width"], self.playButton["height"])),
                    (self.playButton["x"], self.playButton["y"]))
        screen.blit(pygame.transform.scale(restart_button, (self.restartButton["width"], self.restartButton["height"])),
                    (self.restartButton["x"], self.restartButton["y"]))

    def handle_click(self, x, y):
        global state
        if x > self.backButton["x"] and x < self.backButton["x"] + self.backButton["width"] and y > self.backButton[
            "y"] and y < self.backButton["y"] + self.backButton["height"]:
            button_click.stop()
            button_click.play()
            state = 2
        if x > self.playButton["x"] and x < self.playButton["x"] + self.playButton["width"] and y > self.playButton[
            "y"] and y < self.playButton["y"] + self.playButton["height"]:
            button_click.stop()
            button_click.play()
            pygame.mixer.music.unpause()
            global continued
            continued = False
            state = 1
        if x > self.restartButton["x"] and x < self.restartButton["x"] + self.restartButton["width"] and y > \
                self.restartButton[
                    "y"] and y < self.restartButton["y"] + self.restartButton["height"]:
            button_click.stop()
            button_click.play()
            pygame.mixer.music.unpause()
            state = 1


class Menu:
    def __init__(self):
        self.playButton = {"x": (screen_width - 200) / 2,
                           "y": (screen_height - 75) / 2 + 150,
                           "width": 200,
                           "height": 75
                           }
        self.prevButton = {"x": (screen_width - 50) / 2 - 75,
                           "y": (screen_height - 50) / 2,
                           "width": 50,
                           "height": 50
                           }
        self.nextButton = {"x": (screen_width - 50) / 2 + 75,
                           "y": (screen_height - 50) / 2,
                           "width": 50,
                           "height": 50
                           }

    def render(self):
        game_text = "SNAKE"
        high_score_text = "HIGHSCORE: " + str(high_score)
        play_text = "PLAY"
        font_1 = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 60)
        font_2 = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 25)
        game_text_render = font_1.render(game_text, True, (255, 255, 255))
        high_score_text_render = font_2.render(high_score_text, True, (255, 255, 255))
        play_text_render = font_2.render(play_text, True, (255, 255, 255))
        text_width_1, text_height_1 = font_1.size(game_text)
        text_width_2, text_height_2 = font_2.size(high_score_text)
        text_width_3, text_height_3 = font_2.size(play_text)
        screen.blit(game_text_render, ((screen_width - text_width_1) / 2, 25))
        screen.blit(high_score_text_render, (10, screen_height - text_height_2 - 10))
        screen.blit(pygame.transform.scale(button, (self.playButton["width"], self.playButton["height"])),
                    (self.playButton["x"], self.playButton["y"]))
        screen.blit(pygame.transform.scale(previous_button, (self.prevButton["width"], self.prevButton["height"])),
                    (self.prevButton["x"], self.prevButton["y"]))
        screen.blit(pygame.transform.scale(next_button, (self.nextButton["width"], self.nextButton["height"])),
                    (self.nextButton["x"], self.nextButton["y"]))
        pygame.draw.circle(screen, colors[selected_color],
                           ((screen_width - 50) / 2 + 25, (screen_height - 50) / 2 + 25), 25)
        screen.blit(play_text_render, ((screen_width - text_width_3) / 2, (screen_height - text_height_3) / 2 + 150))

    def handle_click(self, x, y):
        global state
        global selected_color
        if x > self.playButton["x"] and x < self.playButton["x"] + self.playButton["width"] and y > self.playButton[
            "y"] and y < self.playButton["y"] + self.playButton["height"]:
            button_click.stop()
            button_click.play()
            state = 1
        if x > self.prevButton["x"] and x < self.prevButton["x"] + self.prevButton["width"] and y > self.prevButton[
            "y"] and y < self.prevButton["y"] + self.prevButton["height"]:
            button_click.stop()
            button_click.play()
            selected_color -= 1
            if selected_color < 0:
                selected_color = len(colors) - 1
        if x > self.nextButton["x"] and x < self.nextButton["x"] + self.nextButton["width"] and y > self.nextButton[
            "y"] and y < self.nextButton["y"] + self.nextButton["height"]:
            button_click.stop()
            button_click.play()
            selected_color += 1
            if selected_color >= len(colors):
                selected_color = 0


class Game:
    def __init__(self):
        self.snake = Snake()
        self.food = Food(random.randint(50, screen_width - 50), random.randint(50, screen_height - 50))
        self.pauseButton = {"x": screen_width - 60,
                            "y": 10,
                            "width": 50,
                            "height": 50
                            }

    def update(self):
        self.snake.update()
        self.food.update()
        self.collision()

    def draw(self):
        self.snake.draw()
        self.food.draw()
        screen.blit(
            pygame.transform.scale(menu_button, (self.pauseButton["width"], self.pauseButton["height"])),
            (self.pauseButton["x"], self.pauseButton["y"]))

    def collision(self):
        global state
        global score
        if self.two_circle_collision(self.snake.x[0], self.food.x, self.snake.y[0], self.food.y, self.snake.size,
                                     self.food.size):
            food_eat.stop()
            food_eat.play()
            self.change_position()
            self.snake.increase_length()
            score += 1

        for i in range(15, self.snake.length):
            if self.two_circle_collision(self.snake.x[0], self.snake.x[i], self.snake.y[0], self.snake.y[i],
                                         self.snake.size, self.snake.size):
                state = 2
                self.set_highscore()

        if self.snake.x[0] + self.snake.size >= screen_width or self.snake.x[0] - self.snake.size <= 0 or self.snake.y[
            0] + self.snake.size >= screen_height or self.snake.y[0] - self.snake.size <= 0:
            state = 2
            self.set_highscore()

    @staticmethod
    def two_circle_collision(x1, x2, y1, y2, s1, s2):
        distance = math.sqrt((x1 - x2) ** 2 + (y1 - y2) ** 2)
        return distance < s1 + s2

    def change_position(self):
        self.food.x = random.randint(50, screen_width - 50)
        self.food.y = random.randint(50, screen_height - 50)

    @staticmethod
    def set_highscore():
        global high_score
        if high_score < score:
            high_score = score

    @staticmethod
    def show_score():
        font = pygame.font.Font("C:/WINDOWS/FONTS/IMPACT.TTF", 20)
        score_text = font.render("SCORE: " + str(score), True, (255, 255, 255))
        screen.blit(score_text, (10, 10))

    def reset_game(self):
        global score
        score = 0
        self.change_position()
        self.snake.length = 1
        self.snake.x = [screen_width / 2] * self.snake.length
        self.snake.y = [screen_height / 2] * self.snake.length
        self.snake.velX = 5
        self.snake.velY = 0
        self.snake.direction = "right"

    def handle_click(self, x, y):
        global state
        if x > self.pauseButton["x"] and x < self.pauseButton["x"] + self.pauseButton["width"] and y > \
                self.pauseButton["y"] and y < self.pauseButton["y"] + self.pauseButton["height"]:
            button_click.stop()
            button_click.play()
            pygame.mixer.music.pause()
            state = 3


class Snake:
    def __init__(self):
        self.length = 1
        self.size = 25
        self.x = [screen_width / 2] * self.length
        self.y = [screen_height / 2] * self.length
        self.velX = 5
        self.velY = 0
        self.direction = "right"

    def update(self):
        for i in range(self.length - 1, 0, -1):
            self.x[i] = self.x[i - 1]
            self.y[i] = self.y[i - 1]

        self.x[0] += self.velX
        self.y[0] += self.velY

    def draw(self):
        for i in range(self.length):
            pygame.draw.circle(screen, colors[selected_color], (self.x[i], self.y[i]), self.size)

    def check_input(self, keys):
        if keys[pygame.K_UP] or keys[pygame.K_w]:
            if self.direction != "bottom" and self.direction != "top":
                self.velY = -5
                self.velX = 0
                self.direction = "top"

        if keys[pygame.K_LEFT] or keys[pygame.K_a]:
            if self.direction != "right" and self.direction != "left":
                self.velX = -5
                self.velY = 0
                self.direction = "left"

        if keys[pygame.K_RIGHT] or keys[pygame.K_d]:
            if self.direction != "right" and self.direction != "left":
                self.velX = 5
                self.velY = 0
                self.direction = "right"

        if keys[pygame.K_DOWN] or keys[pygame.K_s]:
            if self.direction != "bottom" and self.direction != "top":
                self.velY = 5
                self.velX = 0
                self.direction = "bottom"

    def increase_length(self):
        self.length += 1
        self.x.append(self.x[len(self.x) - 1])
        self.y.append(self.y[len(self.y) - 1])


class Food:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.size = 15

    def update(self):
        pass

    def draw(self):
        pygame.draw.circle(screen, (255, 0, 0), (self.x, self.y), self.size)


game = Game()
menu = Menu()
pause = Pause()


def fetch_file():
    global high_score
    global selected_color
    try:
        with open('data.txt', 'r') as f:
            contents = f.read()
            values = contents.split(',')
            high_score = int(values[0])
            selected_color = int(values[1])
    except FileNotFoundError:
        high_score = 0
        selected_color = 0

    animate()


def animate():
    running = True
    key_pressed = False

    switch_to_game = True
    switch_to_menu = True
    switch_to_pause = True

    while running:

        screen.fill((0, 0, 0))

        mouse_x, mouse_y = pygame.mouse.get_pos()

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                with open('data.txt', 'w') as f:
                    f.write(f"{high_score},{selected_color}")
                running = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                if state == 1:
                    game.handle_click(mouse_x, mouse_y)
                elif state == 2:
                    menu.handle_click(mouse_x, mouse_y)
                elif state == 3:
                    pause.handle_click(mouse_x, mouse_y)

        if event.type == pygame.KEYDOWN:
            keys = pygame.key.get_pressed()
            if not key_pressed:
                game.snake.check_input(keys)
                key_pressed = True
        elif event.type == pygame.KEYUP:
            key_pressed = False

        if state == 1:
            if switch_to_game:
                global continued
                if continued:
                    game.reset_game()
                    pygame.mixer.music.load('Assets/Audio/game_sound.wav')
                    pygame.mixer.music.play(-1)
                continued = True
                switch_to_game = False
            switch_to_menu = True
            switch_to_pause = True
            game.update()
            game.draw()
            game.show_score()
        elif state == 2:
            if switch_to_menu:
                pygame.mixer.music.load('Assets/Audio/menu_sound.mp3')
                pygame.mixer.music.play(-1)
                switch_to_menu = False
            switch_to_game = True
            switch_to_pause = True
            menu.render()
        elif state == 3:
            if switch_to_pause:
                switch_to_pause = False
            switch_to_game = True
            switch_to_menu = True
            pause.render()

        pygame.display.flip()

        clock.tick(fps)


fetch_file()
