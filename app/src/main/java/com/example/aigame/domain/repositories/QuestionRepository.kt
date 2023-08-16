package com.example.aigame.domain.repositories

import com.example.aigame.data.services.RetrofitMS
import com.example.aigame.domain.entities.ChapterEntity
import com.example.aigame.domain.entities.InterfaceResources
import com.example.aigame.domain.entities.Option
import com.example.aigame.domain.entities.mapChapterToChapterEntity
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val questionService: RetrofitMS
) {

    suspend fun getChapter(chapterId: String): ChapterEntity? {
        return try {
            mapChapterToChapterEntity(questionService.getChapter(chapterId, "cULuJB9amm39kEN3hFViaahdnmkAu3616KXiQZG8"))
        } catch (e: Exception) {
            println("ChatGame error "+e.message)
            createMockChapterResponse(chapterId)
        }
    }

    private fun createMockChapterResponse(userId: String): ChapterEntity {
        val interfaceResources = InterfaceResources(
            title = "Raccoon",
            subtitle = "of the evil death",
            image = "https://static.wikia.nocookie.net/clash-of-clans/images/c/c5/Dragón_info.png/revision/latest/scale-to-width-down/120?cb=20210819010118&path-prefix=es"
        )

        // Create mock Options
        val option1 = Option(
            question = "you find the raccoon of the evil death, he tries to rape you, what do you want to do?",
            options = listOf(
                Option(
                    text = "Let it rape you",
                    question = "Question for Option 2",
                    options = null
                ),
                Option(
                    text = "Take the wheels in the rape",
                    question = "You star to enjoy it, and got in a mad state that tries to fuck other animals in the surrounded area",
                    options = listOf(
                        Option(
                            text = "Rape a bunny",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        ),
                        Option(
                            text = "Rape a chicken",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        ),
                        Option(
                            text = "Rape a squirrel",
                            question = "You enjoy it, good for you you sick bastard",
                            nextCanonicalEventId = "1",
                            options = null
                        )
                    )
                ),
                Option(
                    text = "Run like a little bunny",
                    question = "you are so gay, you die of venereal disease",
                    options = null
                ),
            )
        )



        val root = Option(
            text = "Start",
            question = "You find yourself in a tense courtroom facing off against the notorious mafia boss. What do you do?",
            options = listOf(
                Option(
                    text = "Press for Evidence",
                    question = "You present a strong case with compelling evidence against the mafia boss. The judge seems interested. What's your next move?",
                    options = listOf(
                        Option(
                            text = "Call Witness",
                            question = "You call a key witness to the stand, but they seem nervous. How do you proceed?",
                            options = listOf(
                                Option(
                                    text = "Offer Protection",
                                    question = "You promise the witness protection. They testify against the mafia, revealing crucial information. The courtroom is buzzing. What's your next step?",
                                    options = listOf(
                                        Option(
                                            text = "Expose Underworld Connections",
                                            question = "You reveal the mafia's deep-rooted connections to criminal underworld, shocking everyone. The jury looks convinced. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Reveal Threats",
                                            question = "You disclose the threats made to the witness, showing the mafia's influence. The jury seems concerned. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Present Hard Evidence",
                                            question = "You present undeniable evidence of the mafia's involvement, leaving no room for doubt. The courtroom is in awe. How do you wrap up your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Cross-Examine Witness",
                                    question = "You aggressively cross-examine the witness, but they become hostile. What's your strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Expose Inconsistencies",
                                            question = "You point out inconsistencies in the witness's story, making them look unreliable. The jury seems skeptical. What's your next move?",
                                            options = listOf(
                                                Option(
                                                    text = "Present Alibi",
                                                    question = "You present an alibi that contradicts the witness's claims. Doubt creeps in. What's your next step?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                ),
                                                Option(
                                                    text = "Call Expert",
                                                    question = "You call an expert to debunk the witness's testimony. The jury appears unsure. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                ),
                                                Option(
                                                    text = "Unearth Motive",
                                                    question = "You uncover the witness's hidden motive to lie. The jury starts to have doubts. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                )
                                            )
                                        ),
                                        Option(
                                            text = "Seek Empathy",
                                            question = "You take a sympathetic approach, winning the witness's trust. How do you proceed?",
                                            options = listOf(
                                                Option(
                                                    text = "Reveal Witness's Fear",
                                                    question = "You reveal the witness's fear of mafia retaliation, gaining sympathy. The jury is moved. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Show Witness's Vulnerability",
                                                    question = "You highlight the witness's vulnerability, melting the jury's hearts. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Connect Emotionally",
                                                    question = "You share an emotional story, forging a deep connection with the witness. The courtroom is moved. How do you conclude your case?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                )
                                            )
                                        ),
                                        Option(
                                            text = "Challenge Witness's Credibility",
                                            question = "You challenge the witness's credibility, revealing their shady past. How do you proceed?",
                                            options = listOf(
                                                Option(
                                                    text = "Present Previous Lies",
                                                    question = "You expose the witness's history of lying under oath. The jury starts to doubt their testimony. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Question Witness's Motive",
                                                    question = "You question the witness's motive for testifying, making the jury suspicious. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Reveal Witness's Criminal Ties",
                                                    question = "You reveal the witness's ties to the criminal underworld, casting doubt on their honesty. The jury is intrigued. How do you conclude your case?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                )
                                            )
                                        )
                                    )
                                ),
                                Option(
                                    text = "Seek Plea Deal",
                                    question = "You offer the witness a plea deal in exchange for cooperation. How do you proceed?",
                                    options = listOf(
                                        Option(
                                            text = "Reduce Charges",
                                            question = "You promise reduced charges in exchange for cooperation. The witness agrees to testify. What's your next move?",
                                            options = listOf(
                                                Option(
                                                    text = "Present Testimony",
                                                    question = "The witness's testimony supports your case strongly. The jury is attentive. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Corroborate Testimony",
                                                    question = "You present evidence that corroborates the witness's testimony. The jury seems convinced. How do you conclude your case?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Appeal to Sympathy",
                                                    question = "You appeal to the jury's sympathy, making them empathize with the witness. The courtroom atmosphere changes. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                )
                                            )
                                        ),
                                        Option(
                                            text = "Promise Immunity",
                                            question = "You offer the witness complete immunity in exchange for their testimony. The witness agrees but demands proof. What's your next move?",
                                            options = listOf(
                                                Option(
                                                    text = "Reveal Witness Protection",
                                                    question = "You reveal the witness protection plan, ensuring their safety. The witness agrees to testify openly. How do you proceed?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Expose Mafia's Weakness",
                                                    question = "You expose a weakness within the mafia, proving their vulnerability. The witness is convinced and agrees to testify. How do you conclude your case?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                ),
                                                Option(
                                                    text = "Elicit Empathy",
                                                    question = "You share a heartfelt story, eliciting empathy from the witness. They agree to testify openly. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "victory"
                                                )
                                            )
                                        ),
                                        Option(
                                            text = "Threaten Witness",
                                            question = "You subtly threaten the witness with legal consequences. How do you proceed?",
                                            options = listOf(
                                                Option(
                                                    text = "Show Legal Precedent",
                                                    question = "You present legal precedents to support your threat. The witness starts to feel pressured. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                ),
                                                Option(
                                                    text = "Invoke Witness's Fear",
                                                    question = "You invoke the witness's fear of the mafia, making them nervous. The jury notices their discomfort. What's your next strategy?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                ),
                                                Option(
                                                    text = "Display Mafia's Reach",
                                                    question = "You provide evidence of the mafia's reach, convincing the witness of their power. The jury watches closely. What's your next move?",
                                                    options = null,
                                                    nextCanonicalEventId = "defeat"
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            text = "Introduce Evidence",
                            question = "You introduce compelling evidence, catching the mafia boss off guard. What's your next strategy?",
                            options = listOf(
                                Option(
                                    text = "Reveal Mafia's Weakness",
                                    question = "You expose the mafia's Achilles' heel, leaving the boss visibly unsettled. The jury senses an opportunity. How do you proceed?",
                                    options = listOf(
                                        Option(
                                            text = "Present Testimonies",
                                            question = "You present testimonies that highlight the mafia's weakness. The jury's attention is captured. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Expose Mafia's Vulnerability",
                                            question = "You reveal a vulnerability within the mafia's operation. The jury starts to lean in your favor. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Unearth Undercover Agent",
                                            question = "You expose an undercover agent within the mafia's ranks, shaking their foundation. The jury is intrigued. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Present Definitive Proof",
                                    question = "You present irrefutable proof of the mafia's crimes. The courtroom is tense. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Call Expert Witness",
                                            question = "You call an expert witness to explain the significance of the evidence. The jury seems impressed. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Appeal to Justice",
                                            question = "You make a passionate appeal to justice, urging the jury to consider the evidence. The courtroom atmosphere shifts. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Invoke Public Outrage",
                                            question = "You invoke public outrage over the mafia's crimes, swaying the jury's emotions. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Highlight Motive",
                                    question = "You highlight the mafia's motive behind the crime. What's your next strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Present Motive Analysis",
                                            question = "You present a detailed analysis of the mafia's motive, convincing the jury of their guilt. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Show Victim Impact",
                                            question = "You bring the victim's family to the stand, showing the impact of the crime. The jury empathizes with them. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Expose Mafia's Greed",
                                            question = "You expose the mafia's greed as the driving force behind the crime. The jury starts to connect the dots. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            text = "Invoke Legal Loophole",
                            question = "You exploit a legal loophole, causing confusion in the courtroom. What's your next strategy?",
                            options = listOf(
                                Option(
                                    text = "Present Technicality",
                                    question = "You present a technicality that raises doubts about the mafia's involvement. The jury starts to question the case. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Clarify Technicality",
                                            question = "You clarify the technicality, reassuring the jury that it doesn't affect the core evidence. The atmosphere relaxes. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Counter Technicality",
                                            question = "You counter the technicality with stronger evidence, nullifying its impact. The jury's confidence in the case is restored. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Appeal to Common Sense",
                                            question = "You appeal to the jury's common sense, highlighting that the technicality is irrelevant to the mafia's guilt. The jury seems convinced. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Challenge Evidence",
                                    question = "You challenge the authenticity of the evidence, raising doubts about its validity. What's your next strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Provide Chain of Custody",
                                            question = "You provide a detailed chain of custody for the evidence, establishing its credibility. The jury's skepticism diminishes. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Call Expert Forensic Witness",
                                            question = "You call an expert forensic witness to verify the evidence's authenticity. The jury seems reassured. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Explain Evidence's Significance",
                                            question = "You explain the significance of the evidence within the broader context of the case. The jury starts to see the bigger picture. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Manipulate Perception",
                                    question = "You manipulate the courtroom's perception, causing confusion among the attendees. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Divert Attention",
                                            question = "You divert the jury's attention to a trivial matter, leaving them bewildered. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "defeat"
                                        ),
                                        Option(
                                            text = "Evoke Doubt",
                                            question = "You evoke doubt by introducing irrelevant information. The jury's confidence wavers. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "defeat"
                                        ),
                                        Option(
                                            text = "Shift Focus Back",
                                            question = "You quickly shift the focus back to the core evidence, dispelling confusion. The jury regains their focus. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Option(
                    text = "Challenge Mafia's Power",
                    question = "You challenge the mafia's power directly, aiming to expose their influence. What's your strategy?",
                    options = listOf(
                        Option(
                            text = "Reveal Extensive Network",
                            question = "You reveal the mafia's extensive network, highlighting their ability to manipulate various sectors. The jury is in awe. What's your next move?",
                            options = listOf(
                                Option(
                                    text = "Call Whistleblower",
                                    question = "You call a former mafia member turned whistleblower to testify. The jury's interest is piqued. What's your next strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Detail Criminal Operations",
                                            question = "The whistleblower details the mafia's criminal operations, revealing their reach. The jury starts to see the mafia's power. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Expose Mafia's Control",
                                            question = "You expose how the mafia controls key figures, shaking the jury's confidence in those figures. The courtroom atmosphere changes. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Reveal Financial Influence",
                                            question = "You reveal the mafia's financial influence, showing how they manipulate markets. The jury starts to understand the extent of their power. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Expose Mafia's Political Ties",
                                    question = "You expose the mafia's political ties, highlighting their ability to influence legislation. The jury starts to grasp the mafia's reach. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Present Political Testimonies",
                                            question = "You present testimonies from politicians who were pressured by the mafia. The jury's skepticism diminishes. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Demonstrate Policy Manipulation",
                                            question = "You demonstrate how the mafia manipulated policies to their advantage, shocking the jury. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Reveal Political Blackmail",
                                            question = "You reveal the mafia's blackmail tactics against politicians, leaving the jury in disbelief. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Expose Mafia's Media Control",
                                    question = "You expose the mafia's control over media, revealing their ability to shape public perception. The jury starts to question their beliefs. What's your strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Present Manipulated News",
                                            question = "You present evidence of manipulated news stories to favor the mafia. The jury's trust in media falters. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Uncover Media Threats",
                                            question = "You uncover how the mafia uses threats to control media content, leaving the jury concerned. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Reveal Censorship",
                                            question = "You reveal instances of media censorship by the mafia, alarming the jury. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            text = "Challenge Mafia's Strategy",
                            question = "You challenge the mafia's strategic approach, aiming to expose their planning. What's your strategy?",
                            options = listOf(
                                Option(
                                    text = "Expose Weakness in Operation",
                                    question = "You expose a weakness in the mafia's operation, making them uneasy. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Present Infiltration Plan",
                                            question = "You present a plan of how you infiltrated the mafia, gaining credibility. The jury's attention is captured. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Reveal Operational Mistake",
                                            question = "You reveal a mistake the mafia made in their operation, damaging their credibility. The jury starts to question their competence. How do you proceed?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Uncover Traitor",
                                            question = "You uncover a traitor within the mafia's ranks, causing chaos. The jury is intrigued. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Deconstruct Mafia's Strategy",
                                    question = "You deconstruct the mafia's strategy, highlighting flaws in their planning. What's your next strategy?",
                                    options = listOf(
                                        Option(
                                            text = "Analyze Failed Attempts",
                                            question = "You analyze previous failed attempts by the mafia, revealing their patterns. The jury starts to doubt their capability. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Show Strategy Ineffectiveness",
                                            question = "You demonstrate how the mafia's strategy has been ineffective, leaving the jury skeptical. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Expose Counterstrategy",
                                            question = "You expose a counterstrategy that thwarted the mafia's plans, impressing the jury. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                ),
                                Option(
                                    text = "Reveal Betrayal",
                                    question = "You reveal internal betrayal within the mafia, undermining their unity. What's your next move?",
                                    options = listOf(
                                        Option(
                                            text = "Call Betrayer to Testify",
                                            question = "You call the betrayer within the mafia to testify against their former comrades. The jury's curiosity is piqued. What's your next strategy?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Uncover Motive for Betrayal",
                                            question = "You uncover the motive behind the betrayal, exposing the mafia's internal conflicts. The jury starts to doubt their unity. What's your next move?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        ),
                                        Option(
                                            text = "Expose Mafia's Suspicion",
                                            question = "You reveal how the mafia's internal paranoia weakened their operation, intriguing the jury. How do you conclude your case?",
                                            options = null,
                                            nextCanonicalEventId = "victory"
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Option(
                    text = "Seek Outside Help",
                    question = "You seek help from external sources to build your case against the mafia boss. What's your strategy?",
                    options = listOf(
                        Option(
                            text = "Call Expert Witness",
                            question = "You call an expert witness who can provide specialized insight into the mafia's operations. The jury's interest is piqued. What's your next move?",
                            options = listOf(
                                Option(
                                    text = "Present Expert Testimony",
                                    question = "The expert witness testifies, shedding light on the mafia's operations. The jury is captivated. What's your next strategy?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Uncover Mafia's Cover-up",
                                    question = "The expert reveals how the mafia covered up their crimes, leaving the jury intrigued. How do you proceed?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Expose Expert Witness Threats",
                                    question = "You reveal that the expert witness received threats from the mafia, highlighting their desperation. The jury's sympathy grows. How do you conclude your case?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                )
                            )
                        ),
                        Option(
                            text = "Collaborate with Undercover Agent",
                            question = "You collaborate with an undercover agent who has infiltrated the mafia. What's your next strategy?",
                            options = listOf(
                                Option(
                                    text = "Present Undercover Agent's Testimony",
                                    question = "The undercover agent testifies, providing firsthand accounts of the mafia's operations. The jury's attention is captured. What's your next move?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Uncover Mafia's Suspicion",
                                    question = "The undercover agent reveals how the mafia grew suspicious, showing their paranoia. The jury starts to see cracks in the mafia's facade. How do you proceed?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Expose Agent's Sacrifices",
                                    question = "You reveal the personal sacrifices the undercover agent made for the case, evoking sympathy from the jury. How do you conclude your case?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                )
                            )
                        ),
                        Option(
                            text = "Seek Informant's Testimony",
                            question = "You seek testimony from an informant who has insider information about the mafia. What's your next strategy?",
                            options = listOf(
                                Option(
                                    text = "Present Informant's Insights",
                                    question = "The informant provides valuable insights into the mafia's inner workings. The jury's interest is piqued. What's your next move?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Reveal Informant's Motive",
                                    question = "You reveal the informant's motive for testifying, showing their desire for justice. The jury starts to trust their testimony. How do you proceed?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                ),
                                Option(
                                    text = "Expose Mafia's Retaliation",
                                    question = "You expose the threats the mafia made against the informant, highlighting their desperation. The jury sympathizes with the informant. What's your next move?",
                                    options = null,
                                    nextCanonicalEventId = "victory"
                                )
                            )
                        )
                    )
                )
            )
        )

        val root2 = Option(
            text = "Start",
            question = "You're a detective facing off against a powerful mafia boss in court. What's your strategy?",
            options = listOf(
                Option(
                    text = "Present Evidence",
                    question = "You present strong evidence of the mafia's crimes. The courtroom's attention is on you. What's your next move?",
                    options = listOf(
                        Option(
                            text = "Call Key Witness",
                            question = "You call a key witness who can expose the mafia's operations. How do you proceed?",
                            options = listOf(
                                Option(
                                    text = "Protect Witness",
                                    question = "You offer the witness protection, gaining their trust. They testify against the mafia.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Expose Threats",
                                    question = "You reveal the threats made by the mafia, showing their influence. The jury's sympathy grows.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Present Hard Evidence",
                                    question = "You present undeniable evidence of the mafia's crimes. The courtroom is in shock.",
                                    options = null,
                                    nextCanonicalEventId = null
                                )
                            )
                        ),
                        Option(
                            text = "Challenge Witness",
                            question = "You cross-examine the witness, but they become hostile. What's your strategy?",
                            options = listOf(
                                Option(
                                    text = "Expose Inconsistencies",
                                    question = "You reveal inconsistencies in the witness's testimony, casting doubt. The jury starts to question the witness.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Question Motive",
                                    question = "You question the witness's motive for testifying, casting doubt on their intentions.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Reveal Ties",
                                    question = "You reveal the witness's ties to the criminal underworld, leaving the jury skeptical.",
                                    options = null,
                                    nextCanonicalEventId = null
                                )
                            )
                        ),
                        Option(
                            text = "Seek Empathy",
                            question = "You take a sympathetic approach, winning the witness's trust. How do you proceed?",
                            options = listOf(
                                Option(
                                    text = "Reveal Witness's Fear",
                                    question = "You reveal the witness's fear of mafia retaliation, gaining sympathy. The jury is moved.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Show Vulnerability",
                                    question = "You highlight the witness's vulnerability, swaying the jury's emotions.",
                                    options = null,
                                    nextCanonicalEventId = null
                                ),
                                Option(
                                    text = "Connect Emotionally",
                                    question = "You share an emotional story, forging a deep connection with the witness. The courtroom is touched.",
                                    options = null,
                                    nextCanonicalEventId = null
                                )
                            )
                        )
                    )
                ),
                Option(
                    text = "Challenge Mafia's Power",
                    question = "You challenge the mafia's power directly, aiming to expose their influence. What's your strategy?",
                    options = listOf(
                        Option(
                            text = "Reveal Network",
                            question = "You reveal the mafia's extensive network, showing their ability to manipulate various sectors. The jury's attention is captured.",
                            options = null,
                            nextCanonicalEventId = null
                        ),
                        Option(
                            text = "Challenge Strategy",
                            question = "You challenge the mafia's strategic approach, aiming to expose their planning.",
                            options = null,
                            nextCanonicalEventId = null
                        ),
                        Option(
                            text = "Seek Help",
                            question = "You seek help from external sources to build your case against the mafia boss.",
                            options = null,
                            nextCanonicalEventId = null
                        )
                    )
                ),
                Option(
                    text = "Confront Mafia's Greed",
                    question = "You confront the mafia's greed, aiming to expose their motives.",
                    options = listOf(
                        Option(
                            text = "Present Motive Analysis",
                            question = "You present a detailed analysis of the mafia's motive, convincing the jury of their guilt.",
                            options = null,
                            nextCanonicalEventId = null
                        ),
                        Option(
                            text = "Reveal Hidden Motive",
                            question = "You reveal a hidden motive behind the crime, leaving the jury intrigued.",
                            options = null,
                            nextCanonicalEventId = null
                        ),
                        Option(
                            text = "Expose Personal Gain",
                            question = "You expose how the mafia's actions were driven by personal gain.",
                            options = null,
                            nextCanonicalEventId = null
                        )
                    )
                )
            )
        )

        val level3Ending1 = Option(
            "Go to Jail",
            "With the incriminating evidence presented, the judge has no choice but to send the mafia boss and his associates to jail. Justice is served, and the city breathes a sigh of relief.",
            "",
            null,
            "ending_jail"
        )

        val level3Ending2 = Option(
            "Go Free",
            "Despite the detective's efforts, the defense lawyer manages to create enough doubt, and the mafia boss and his associates are set free. The city falls into despair as the criminals evade justice.",
            "",
            null,
            "ending_free"
        )

        val level3Ending3 = Option(
            "Confrontation",
            "As tensions rise, the mafia boss pulls out a hidden gun and shoots the detective. The courtroom turns into chaos as the detective's life fades away. The mafia's grip on the city tightens.",
            "",
            null,
            "ending_death"
        )

        val level2Option1 = Option(
            "Expose Informant",
            "The detective decides to expose an informant who has vital information about the mafia's activities. This leads to a risky confrontation with the mafia, putting everyone's lives in danger.",
            "Do you want to expose the informant and confront the mafia?",
            listOf(level3Ending1, level3Ending2, level3Ending3)
        )

        val level2Option2 = Option(
            "Gather Financial Records",
            "The detective digs deep into the mafia's financial records, uncovering a web of illegal transactions and money laundering. This information could bring down the mafia's empire.",
            "Do you want to focus on gathering financial records?",
            listOf(level3Ending1, level3Ending2, level3Ending3)
        )

        val level2Option3 = Option(
            "Track Carmen's Movements",
            "The detective decides to tail Carmen, a key lieutenant in the mafia. This leads to discovering a hidden meeting place and a chance to catch the mafia off guard.",
            "Do you want to track Carmen's movements and ambush the mafia?",
            listOf(level3Ending1, level3Ending2, level3Ending3)
        )

        val level1Option1 = Option(
            "Expose Informant",
            "As you step into the courtroom, tension fills the air. The detective must choose wisely to expose an informant or gather financial records to build a solid case against the mafia.",
            "How will you start building your case?",
            listOf(level2Option1, level2Option2, level2Option3)
        )

        val level1Option2 = Option(
            "Confront Mafia's Lawyer",
            "The detective confronts the mafia's lawyer, Antonio, who tries to intimidate and manipulate the court proceedings. It's a battle of wits to prevent the lawyer from obstructing justice.",
            "Do you want to confront the mafia's lawyer in court?",
            listOf(level2Option1, level2Option2, level2Option3)
        )

        val level1Option3 = Option(
            "Search for Key Evidence",
            "Before the trial, the detective decides to search for key evidence that could directly link the mafia to their criminal activities. This could be the key to securing a conviction.",
            "Do you want to search for crucial evidence before the trial?",
            listOf(level2Option1, level2Option2, level2Option3)
        )

        val introNode = Option(
            "Enter the Courtroom",
            "Detective Johnson enters the courtroom, where the trial against the dangerous mafia syndicate is about to begin. The judge presides over the proceedings, and tension hangs in the air.",
            "As Detective Johnson, how will you approach the beginning of the trial?",
            listOf(level1Option1, level1Option2, level1Option3)
        )

        val intro = Option(
            option = "Enter the courtroom",
            text = "You step into the dimly lit courtroom, the air thick with tension. The judge eyes you as you take your place at the prosecutor's table.",
            question = "How do you begin your opening statement?",
            options = listOf(
                Option(
                    option = "Highlight the evidence",
                    text = "You meticulously present the evidence, detailing how Antonio, Carmen, and Vito are linked to the mafia's operations.",
                    question = "Do you emphasize their financial transactions or their known associates?",
                    options = listOf(
                        Option(
                            option = "Financial transactions",
                            text = "You delve into their intricate financial web, tracing money flows that tie them to illegal activities.",
                            question = "How do you connect the money trail to the mafia's activities?",
                            options = listOf(
                                Option(
                                    option = "Show payments to known mafia members",
                                    text = "You reveal bank transfers directly to individuals with criminal records, leaving no doubt about their affiliation.",
                                    question = "What's your next move?",
                                    nextCanonicalEventId = "ending_success"
                                ),
                                Option(
                                    option = "Demonstrate money laundering scheme",
                                    text = "You explain how the mafia funneled funds through legitimate businesses, masking their illicit gains.",
                                    question = "How do you prove the link between the businesses and the mafia?",
                                    options = listOf(
                                        Option(
                                            option = "Witness testimonies",
                                            text = "You call forth former employees who testify about the mafia's control over the businesses.",
                                            question = "Do you have a compelling closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "Appeal to justice",
                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Option(
                                    option = "Expose hidden assets",
                                    text = "You reveal secret offshore accounts owned by the mafia, solidifying their connection to criminal activities.",
                                    question = "What's your next move?",
                                    nextCanonicalEventId = "ending_success"
                                )
                            )
                        ),
                        Option(
                            option = "Known associates",
                            text = "You focus on their network of known associates, outlining how their relationships implicate them in the mafia's operations.",
                            question = "Which type of associates do you highlight?",
                            options = listOf(
                                Option(
                                    option = "Undercover informants",
                                    text = "You reveal that Antonio, Carmen, and Vito had close ties to known undercover informants within the mafia.",
                                    question = "How do you demonstrate the significance of these connections?",
                                    options = listOf(
                                        Option(
                                            option = "Intercepted communications",
                                            text = "You present wiretap recordings that capture incriminating conversations between the defendants and the informants.",
                                            question = "Do you have a compelling closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "Appeal to justice",
                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Option(
                                    option = "Business partners",
                                    text = "You reveal that Antonio, Carmen, and Vito had shared business ventures with individuals known to be part of the mafia.",
                                    question = "How do you establish a direct link between their business ventures and criminal activities?",
                                    options = listOf(
                                        Option(
                                            option = "Evidence of illegal transactions",
                                            text = "You provide documents that detail suspicious financial transactions between the defendants and their mafia-linked partners.",
                                            question = "Do you have a compelling closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "Appeal to justice",
                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                ),
                                Option(
                                    option = "Family connections",
                                    text = "You reveal that Antonio, Carmen, and Vito have familial ties to known mafia members, indicating their involvement.",
                                    question = "How do you demonstrate the significance of these familial connections?",
                                    options = listOf(
                                        Option(
                                            option = "Shared residences",
                                            text = "You present evidence showing that the defendants shared residences with notorious mafia figures.",
                                            question = "Do you have a compelling closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "Appeal to justice",
                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            option = "Combine financial and associate evidence",
                            text = "You present a comprehensive case, combining financial transactions and known associates to paint a clear picture of mafia involvement.",
                            question = "How do you tie everything together in your closing statement?",
                            options = listOf(
                                Option(
                                    option = "The web of criminality",
                                    text = "With conviction, you illustrate how the financial transactions and associations weave together to form an undeniable web of criminality.",
                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                    options = listOf(
                                        Option(
                                            option = "I'm cautiously optimistic",
                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                            question = "",
                                            nextCanonicalEventId = "ending_success"
                                        ),
                                        Option(
                                            option = "I'm not sure...",
                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                            question = "",
                                            nextCanonicalEventId = "ending_fail"
                                        ),
                                        Option(
                                            option = "I'm confident",
                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                            question = "",
                                            nextCanonicalEventId = "ending_success"
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Option(
                    option = "Appeal to emotions",
                    text = "You start by evoking emotions, describing the impact of the mafia's crimes on innocent people's lives.",
                    question = "Which emotional aspect do you focus on?",
                    options = listOf(
                        Option(
                            option = "Victims' stories",
                            text = "You share heart-wrenching stories of individuals who suffered due to the mafia's actions, making it personal for the jury.",
                            question = "How do you transition from the emotional appeal to concrete evidence?",
                            options = listOf(
                                Option(
                                    option = "Unveil the evidence",
                                    text = "With emotion still hanging in the air, you proceed to present the concrete evidence that ties Antonio, Carmen, and Vito to the mafia's operations.",
                                    question = "Do you highlight their financial transactions or their known associates?",
                                    options = listOf(
                                        Option(
                                            option = "Financial transactions",
                                            text = "You delve into their intricate financial web, tracing money flows that tie them to illegal activities.",
                                            question = "How do you connect the money trail to the mafia's activities?",
                                            options = listOf(
                                                Option(
                                                    option = "Show payments to known mafia members",
                                                    text = "You reveal bank transfers directly to individuals with criminal records, leaving no doubt about their affiliation.",
                                                    question = "What's your next move?",
                                                    nextCanonicalEventId = "ending_success"
                                                ),
                                                Option(
                                                    option = "Demonstrate money laundering scheme",
                                                    text = "You explain how the mafia funneled funds through legitimate businesses, masking their illicit gains.",
                                                    question = "How do you prove the link between the businesses and the mafia?",
                                                    options = listOf(
                                                        Option(
                                                            option = "Witness testimonies",
                                                            text = "You call forth former employees who testify about the mafia's control over the businesses.",
                                                            question = "Do you have a compelling closing statement?",
                                                            options = listOf(
                                                                Option(
                                                                    option = "Appeal to justice",
                                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                                    options = listOf(
                                                                        Option(
                                                                            option = "I'm cautiously optimistic",
                                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm not sure...",
                                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_fail"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm confident",
                                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        )
                                                                    )
                                                                )
                                                            )
                                                        )
                                                    )
                                                ),
                                                Option(
                                                    option = "Expose hidden assets",
                                                    text = "You reveal secret offshore accounts owned by the mafia, solidifying their connection to criminal activities.",
                                                    question = "What's your next move?",
                                                    nextCanonicalEventId = "ending_success"
                                                )
                                            )
                                        ),
                                        Option(
                                            option = "Known associates",
                                            text = "You focus on their network of known associates, outlining how their relationships implicate them in the mafia's operations.",
                                            question = "Which type of associates do you highlight?",
                                            options = listOf(
                                                Option(
                                                    option = "Undercover informants",
                                                    text = "You reveal that Antonio, Carmen, and Vito had close ties to known undercover informants within the mafia.",
                                                    question = "How do you demonstrate the significance of these connections?",
                                                    options = listOf(
                                                        Option(
                                                            option = "Intercepted communications",
                                                            text = "You present wiretap recordings that capture incriminating conversations between the defendants and the informants.",
                                                            question = "Do you have a compelling closing statement?",
                                                            options = listOf(
                                                                Option(
                                                                    option = "Appeal to justice",
                                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                                    options = listOf(
                                                                        Option(
                                                                            option = "I'm cautiously optimistic",
                                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm not sure...",
                                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_fail"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm confident",
                                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        )
                                                                    )
                                                                )
                                                            )
                                                        )
                                                    )
                                                ),
                                                Option(
                                                    option = "Business partners",
                                                    text = "You reveal that Antonio, Carmen, and Vito had shared business ventures with individuals known to be part of the mafia.",
                                                    question = "How do you establish a direct link between their business ventures and criminal activities?",
                                                    options = listOf(
                                                        Option(
                                                            option = "Evidence of illegal transactions",
                                                            text = "You provide documents that detail suspicious financial transactions between the defendants and their mafia-linked partners.",
                                                            question = "Do you have a compelling closing statement?",
                                                            options = listOf(
                                                                Option(
                                                                    option = "Appeal to justice",
                                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                                    options = listOf(
                                                                        Option(
                                                                            option = "I'm cautiously optimistic",
                                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm not sure...",
                                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_fail"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm confident",
                                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        )
                                                                    )
                                                                )
                                                            )
                                                        )
                                                    )
                                                ),
                                                Option(
                                                    option = "Family connections",
                                                    text = "You reveal that Antonio, Carmen, and Vito have familial ties to known mafia members, indicating their involvement.",
                                                    question = "How do you demonstrate the significance of these familial connections?",
                                                    options = listOf(
                                                        Option(
                                                            option = "Shared residences",
                                                            text = "You present evidence showing that the defendants shared residences with notorious mafia figures.",
                                                            question = "Do you have a compelling closing statement?",
                                                            options = listOf(
                                                                Option(
                                                                    option = "Appeal to justice",
                                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                                    options = listOf(
                                                                        Option(
                                                                            option = "I'm cautiously optimistic",
                                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm not sure...",
                                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_fail"
                                                                        ),
                                                                        Option(
                                                                            option = "I'm confident",
                                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                                            question = "",
                                                                            nextCanonicalEventId = "ending_success"
                                                                        )
                                                                    )
                                                                )
                                                            )
                                                        )
                                                    )
                                                )
                                            )
                                        ),
                                        Option(
                                            option = "Combine financial and associate evidence",
                                            text = "You present a comprehensive case, combining financial transactions and known associates to paint a clear picture of mafia involvement.",
                                            question = "How do you tie everything together in your closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "The web of criminality",
                                                    text = "With conviction, you illustrate how the financial transactions and associations weave together to form an undeniable web of criminality.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            option = "Innocence of the accused",
                            text = "You argue that Antonio, Carmen, and Vito are innocent victims of a misunderstanding, presenting alternate explanations for the evidence.",
                            question = "How do you explain away the financial transactions?",
                            options = listOf(
                                Option(
                                    option = "Legitimate business deals",
                                    text = "You provide evidence that the financial transactions were part of legitimate business deals, unrelated to the mafia.",
                                    question = "How do you counter the evidence of their known associates?",
                                    options = listOf(
                                        Option(
                                            option = "Business partnerships",
                                            text = "You explain that the known associates were merely business partners with no criminal ties.",
                                            question = "What about the familial connections?",
                                            options = listOf(
                                                Option(
                                                    option = "Coincidental family ties",
                                                    text = "You suggest that the familial connections were coincidental and not indicative of mafia involvement.",
                                                    question = "Do you have a compelling closing statement?",
                                                    options = listOf(
                                                        Option(
                                                            option = "Appeal to doubt",
                                                            text = "With conviction, you cast doubt on the prosecution's case, urging the jury to consider reasonable doubt.",
                                                            question = "The jury is now deliberating. How confident are you in their decision?",
                                                            options = listOf(
                                                                Option(
                                                                    option = "I'm cautiously optimistic",
                                                                    text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a not guilty verdict. The accused are set free.",
                                                                    question = "",
                                                                    nextCanonicalEventId = "ending_fail"
                                                                ),
                                                                Option(
                                                                    option = "I'm not sure...",
                                                                    text = "The uncertainty gnaws at you as the jury deliberates. Unexpectedly, they return with a guilty verdict.",
                                                                    question = "",
                                                                    nextCanonicalEventId = "ending_success"
                                                                ),
                                                                Option(
                                                                    option = "I'm confident",
                                                                    text = "Despite your confidence, the jury delivers a guilty verdict, convicting Antonio, Carmen, and Vito.",
                                                                    question = "",
                                                                    nextCanonicalEventId = "ending_fail"
                                                                )
                                                            )
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Option(
                    option = "Confront the mafia head-on",
                    text = "You decide to confront the mafia directly, accusing them of their crimes and challenging them to prove their innocence.",
                    question = "How do you present your accusations?",
                    options = listOf(
                        Option(
                            option = "Direct evidence",
                            text = "You present direct evidence of their involvement in criminal activities, backed by testimonies from witnesses and informants.",
                            question = "Do you call any witnesses to testify against them?",
                            options = listOf(
                                Option(
                                    option = "Witness testimonies",
                                    text = "You call forth witnesses who provide firsthand accounts of the mafia's operations, leaving no room for doubt.",
                                    question = "What's your next move?",
                                    options = listOf(
                                        Option(
                                            option = "Cross-examine the mafia members",
                                            text = "You cross-examine Antonio, Carmen, and Vito, exposing their inability to counter the witnesses' claims.",
                                            question = "How do you deliver your closing statement?",
                                            options = listOf(
                                                Option(
                                                    option = "Appeal to justice",
                                                    text = "With conviction, you urge the jury to hold the mafia accountable for their crimes.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            option = "Challenge the mafia leader",
                            text = "You directly challenge the mafia leader, questioning their authority and daring them to prove their legitimacy.",
                            question = "How do you respond when the mafia leader remains silent?",
                            options = listOf(
                                Option(
                                    option = "Present evidence",
                                    text = "You present undeniable evidence of the mafia's criminal activities, demanding that they defend their innocence.",
                                    question = "How do you counter their potential defense?",
                                    options = listOf(
                                        Option(
                                            option = "Refute their alibis",
                                            text = "You systematically dismantle their alibis and explanations, leaving the mafia members speechless.",
                                            question = "What's your next move?",
                                            options = listOf(
                                                Option(
                                                    option = "Defiant closing statement",
                                                    text = "With unwavering resolve, you deliver a powerful closing statement, condemning the mafia's actions and demanding justice.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unexpectedly, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        ),
                        Option(
                            option = "Challenge their authority",
                            text = "You challenge the mafia's authority, highlighting their history of violence and intimidation tactics.",
                            question = "How do you respond when the mafia threatens you?",
                            options = listOf(
                                Option(
                                    option = "Stand firm",
                                    text = "You stand your ground, unfazed by their threats, and continue to present your case against them.",
                                    question = "How do you counter their attempts to discredit your evidence?",
                                    options = listOf(
                                        Option(
                                            option = "Reinforce the evidence",
                                            text = "You reinforce your evidence with additional testimonies and documents, exposing their attempts to manipulate the truth.",
                                            question = "What's your next move?",
                                            options = listOf(
                                                Option(
                                                    option = "Defiant closing statement",
                                                    text = "With unwavering resolve, you deliver a powerful closing statement, condemning the mafia's actions and demanding justice.",
                                                    question = "The jury is now deliberating. How confident are you in their decision?",
                                                    options = listOf(
                                                        Option(
                                                            option = "I'm cautiously optimistic",
                                                            text = "You wait anxiously as the jury weighs the evidence. Moments later, they return with a guilty verdict. Justice prevails!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        ),
                                                        Option(
                                                            option = "I'm not sure...",
                                                            text = "The uncertainty gnaws at you as the jury deliberates. Unfortunately, they return with a not guilty verdict.",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_fail"
                                                        ),
                                                        Option(
                                                            option = "I'm confident",
                                                            text = "Your confidence pays off as the jury swiftly convicts the mafia members. Justice is served!",
                                                            question = "",
                                                            nextCanonicalEventId = "ending_success"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )

        return ChapterEntity(interfaceResources = interfaceResources, branch = intro)
    }
}

