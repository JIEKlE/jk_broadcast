package jiekie.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtil {
    /* prefix */
    public static String getWarnPrefix() {
        return "\uA003 ";
    }

    public static String getBellPrefix() {
        return "\uA004 ";
    }

    /* validate */
    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void notOp(Player player) {
        player.sendMessage(getWarnPrefix() + "권한이 없습니다.");
    }

    public static String wrongCommand() {
        return getWarnPrefix() + "명령어 사용법이 잘못되었습니다.";
    }

    /* broadcast */
    public static void broadcastTest(Player player, String contents) {
        player.sendMessage("");
        player.sendMessage(getBellPrefix() + contents);
        player.sendMessage("");
    }

    public static void broadcast(String contents) {
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(getBellPrefix() + contents);
        Bukkit.broadcastMessage("");
    }

    /* command */
    public static void commandHelper(Player player) {
        player.sendMessage(getWarnPrefix() + "/공지사항 도움말" + ChatColor.GRAY + " : 사용 가능한 명령어를 확인할 수 있습니다.");
    }

    public static void commandList(Player player) {
        player.sendMessage("");
        player.sendMessage(getWarnPrefix() + "공지사항 명령어 목록");
        player.sendMessage("　　　① /공지사항 채팅 내용");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 공지사항을 채팅으로 알립니다.");
        player.sendMessage("　　　② /공지사항 타이틀 내용");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 공지사항을 타이틀로 알립니다.");
        player.sendMessage("　　　③ /공지사항 테스트 내용");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 공지사항을 내용을 본인만 확인할 수 있습니다.");
        player.sendMessage("　　　④ /공지사항 도움말");
        player.sendMessage(ChatColor.GRAY + "　　　　　: 사용 가능한 명령어를 확인할 수 있습니다.");
        player.sendMessage("");
    }
}
