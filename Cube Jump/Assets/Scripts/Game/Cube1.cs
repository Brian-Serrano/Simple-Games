using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.EventSystems;

public class Cube1 : MonoBehaviour
{
    public float jumpForce = 7.0f;
    public GameObject cubeSprite;

    private bool isGrounded = true;
    private bool isJumping = false;

    private Rigidbody2D rb;
    private AudioSource audioPlayer;

    void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        audioPlayer = GetComponent<AudioSource>();
    }

    void Update()
    {
        if (!isJumping && isGrounded && (Input.GetKey(KeyCode.Space) || Input.GetMouseButton(0)) && !EventSystem.current.currentSelectedGameObject)
        {
            audioPlayer.Play();
            
            isGrounded = false;
            isJumping = true;

            rb.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse);

            StartCoroutine(ResetJump());
        }

        if (!isGrounded)
        {
            cubeSprite.transform.Rotate(Vector3.back * 300 * Time.deltaTime);
        }
        else
        {
            Vector3 rotation = cubeSprite.transform.rotation.eulerAngles;
            rotation.z = Mathf.Round(rotation.z / 90) * 90;
            cubeSprite.transform.rotation = Quaternion.Euler(rotation);
        }
    }

    IEnumerator ResetJump()
    {
        yield return new WaitForSeconds(0.2f);
        isJumping = false;
    }

    void OnCollisionStay2D(Collision2D other)
    {
        if (other.gameObject.CompareTag("Ground") || other.gameObject.CompareTag("Block"))
        {
            isGrounded = true;
        }

        string collider = other.gameObject.name;

        if (collider == "leftCollider" || collider == "rightCollider" || collider == "bottomCollider" || other.gameObject.CompareTag("Spike") || other.gameObject.CompareTag("Thorn"))
        {
            GameOver();
        }
    }

    void OnCollisionExit2D(Collision2D other)
    {
        if (other.gameObject.CompareTag("Block") || other.gameObject.CompareTag("Ground"))
        {
            isGrounded = false;
        }
    }

    public void GameOver()
    {
        if (MyVariables.instance.highScore < (int)MyVariables.instance.score)
        {
            MyVariables.instance.highScore = (int)MyVariables.instance.score;
        }
        MyVariables.instance.score = 0;
        MyVariables.instance.obstacleCount = 0;
        Time.timeScale = 1f;
        SaveData.SaveGameData();
        SceneManager.LoadScene("Menu");
    }
}
